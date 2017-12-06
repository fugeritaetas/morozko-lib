package org.morozko.java.mod.db.dao.query.exec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.FieldFactory;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.GenericDAO;
import org.morozko.java.mod.db.dao.RSExtractor;
import org.morozko.java.mod.db.dao.rse.IntegerRSE;

/**
 * 
 * <p>Executes query on Data Base based on a query and param list</p>
 * 
 * @author mfranci
 *
 */
public class QueryExecuror extends GenericDAO {

	public QueryExecuror(ConnectionFactory connectionFactory) {
		super( new BasicDAOFactory( connectionFactory ) );
	}

	
	/**
	 * <p>Given a SQL query in the specified forma, a list of parameters and a result set extractor
	 * load all result returned by the query.</p>
	 * 
	 * @param sql		
	 * @param paramList
	 * @param rse
	 * @return
	 * @throws DAOException
	 */
	public List load( String sql, List paramList, RSExtractor rse ) throws DAOException {
		List list = new ArrayList();
		
		Map paramMap = new HashMap();
		
		Iterator itParam = paramList.iterator();
		while ( itParam.hasNext() ) {
			QueryParam param = (QueryParam) itParam.next();
			paramMap.put( param.getName().toLowerCase() , param.getParam() );
		}
		
		this.getLog().debug( "pre  sql : "+sql );
		
		FieldList fl = new FieldList( new FieldFactory() );
		
		String pat = "(?s)\\$\\{(.*?)\\}";
		
		
		Pattern p = Pattern.compile( pat );
		Matcher m = p.matcher( sql );
		int count = 0;
		while ( m.find() ) {
			String current = m.group().toLowerCase();
			current = current.substring( 2, current.length()-1 );
			Object param = paramMap.get( current );
			fl.addField( param );
			count++;
			LogFacade.getLog().debug( "currente param "+count+" "+current );
		}
		
		sql = sql.replaceAll( pat, "?" );
		
		this.getLog().debug( "post sql : "+sql );

		this.loadAll( list, sql, fl, rse );
		
		return list;
	}
	
	public static void main( String[] args ) {
		
		try { 

			String d = args[0];
			String c = args[1];
			String u = args[2];
			String p = args[3];
			
			ConnectionFactory cf = ConnectionFactoryImpl.newInstance( d, c, u, p );
			
			QueryExecuror qe = new QueryExecuror( cf );
			
			StringBuffer sql = new StringBuffer();
			sql.append( " SELECT ec_risultato_numero_documenti FROM trac_estratto_conto " );
			sql.append( " WHERE ec_risultato_esito = ${esito} " );


	
			
			List paramList = new ArrayList();
			paramList.add( QueryParam.newQueryParam( "esito" , new Integer( 100 ) ) );
			
			List list = qe.load( sql.toString(), paramList, IntegerRSE.DEFAULT );
			
			
			System.out.println( "list : "+list );
			
		} catch (Throwable t) {
			t.printStackTrace( System.out ); 
		}
		
	}
	
}
