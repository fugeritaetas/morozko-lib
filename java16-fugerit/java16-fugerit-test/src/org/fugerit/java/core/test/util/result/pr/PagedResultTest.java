package org.fugerit.java.core.test.util.result.pr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.fugerit.java.core.log.BasicLogObject;
import org.fugerit.java.core.util.result.DefaultPagedResult;
import org.fugerit.java.core.util.result.DefaultVirtualFinder;
import org.fugerit.java.core.util.result.PagedResult;
import org.fugerit.java.core.util.result.VirtualFinder;
import org.fugerit.java.core.util.result.VirtualPageCache;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * 

		Test class for virtual paging, parameters : 
		
		-d ${driverclass}
		-c ${jdbcurl}
		-u ${dbuser}
		-p ${dbpass}
		-query-test " A select "

 * 
 * @author fugerit79
 *
 */
public class PagedResultTest {

	private static BasicLogObject logger = new BasicLogObject();
	
	private static PagedResult<Properties> loadPage( ConnectionFactory cf, VirtualFinder finder, String testSql ) throws SQLException, DAOException {
		Connection conn = cf.getConnection();
		logger.getLogger().log( Level.INFO, "connection >> '"+conn+"'" );
		int perPage = finder.getRealPerPage();
		int currentPage = finder.getRealCurrentPage();
		String pageSql = testSql+" LIMIT "+perPage+" OFFSET "+(currentPage-1)*perPage;
		logger.getLogger().log( Level.INFO, "paged-sql  >> '"+pageSql+"'" );
		List<Properties> list = new ArrayList<Properties>();
		int maxColumns = 5;
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( pageSql );
			ResultSetMetaData md = rs.getMetaData();
			while ( rs.next() ) {
				Properties props = new Properties();
				for ( int k=1; k<=maxColumns; k++ ) {
					props.setProperty( md.getColumnLabel( k ) , rs.getString( k ) );
				}
				list.add( props );
			}
			rs.close();
			stm.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			conn.close();
		}
		PagedResult<Properties> result = DefaultPagedResult.newPagedResult(
				finder.getPerPage(), 100, finder.getCurrentPage(), list, finder.getRealPerPage(), finder.getRealCurrentPage(), testSql );
		return result;
	}

	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgsDefault( args );
			ConnectionFactory cf = ConnArgs.createConnectionFactory( argList );
			String testSql = argList.findArgValue( "query-test" );
			logger.getLogger().log( Level.INFO, "query-test >> '"+testSql+"'" );
			VirtualPageCache<Properties> cache = new VirtualPageCache<Properties>();
			int realPerPage = 25;
			int virtualPerPage = 5;
			int virtualCurrentStart = 4;
			int virtualCurrentEnd = 8;
			// test parameters
			int dbAccess = 0;
			int cacheAccess = 0;
			// test page iteration
			for ( int k=virtualCurrentStart; k<=virtualCurrentEnd; k++ ) {
				int virtualCurrentPage = k;
				int virtualPointer = virtualCurrentPage*virtualPerPage;
				int realCurrentPage = (virtualPointer/realPerPage);
				if ( virtualPointer%realPerPage != 0 ) {
					realCurrentPage++;
				}
				logger.getLogger().log( Level.INFO, "REAL CURRENT PAGE "+realCurrentPage );
				VirtualFinder finder = new DefaultVirtualFinder( virtualPerPage, virtualCurrentPage , realPerPage, realCurrentPage, testSql );
				PagedResult<Properties> realPage = cache.getCachedPage( finder );
				if ( realPage == null ) {
					logger.getLogger().log( Level.INFO, "***************************************************************" );
					logger.getLogger().log( Level.INFO, "***************************************************************" );
					logger.getLogger().log( Level.INFO, "load real page finder -> "+finder );
					logger.getLogger().log( Level.INFO, "***************************************************************" );
					logger.getLogger().log( Level.INFO, "***************************************************************" );
					realPage = loadPage( cf , finder, testSql );
					cache.addPageToCache( realPage );
					dbAccess++;
				} else {
					logger.getLogger().log( Level.INFO, "VIRTUAL PAGE WAS CACHED! "+k );
					cacheAccess++;
				}
				PagedResult<Properties> virtualPage = realPage.getVirtualPage( virtualCurrentPage );
				logger.getLogger().log( Level.INFO, "PRINT VIRTUAL PAGE : "+k+" > "+virtualPage.getCurrentPageSize() );
				Iterator<Properties> it = virtualPage.getPageElements();
				int count = 0;
				while ( it.hasNext() ) {
					count++;
					logger.getLogger().log( Level.INFO, "Element "+count+" -> "+it.next() );
				}	
			}
			logger.getLogger().log( Level.INFO, "dbAccess:"+dbAccess+" cacheAccess:"+cacheAccess );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

