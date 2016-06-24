/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)DatabaseMetaDataWrapper.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.sql
 * @creation   : 05/apr/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.sql.wrapper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DatabaseMetaDataWrapper implements DatabaseMetaData {

	private DatabaseMetaData wrappedDatabaseMetaData;

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#allProceduresAreCallable()
	 */
	public boolean allProceduresAreCallable() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().allProceduresAreCallable();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#allTablesAreSelectable()
	 */
	public boolean allTablesAreSelectable() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().allTablesAreSelectable();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#dataDefinitionCausesTransactionCommit()
	 */
	public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().dataDefinitionCausesTransactionCommit();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#dataDefinitionIgnoredInTransactions()
	 */
	public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().dataDefinitionIgnoredInTransactions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#deletesAreDetected(int)
	 */
	public boolean deletesAreDetected(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().deletesAreDetected(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#doesMaxRowSizeIncludeBlobs()
	 */
	public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().doesMaxRowSizeIncludeBlobs();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getAttributes(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getAttributes(catalog, schemaPattern, typeNamePattern,
				attributeNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getBestRowIdentifier(java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getBestRowIdentifier(catalog, schema, table, scope, nullable);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getCatalogs()
	 */
	public ResultSet getCatalogs() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getCatalogs();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getCatalogSeparator()
	 */
	public String getCatalogSeparator() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getCatalogSeparator();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getCatalogTerm()
	 */
	public String getCatalogTerm() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getCatalogTerm();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getColumnPrivileges(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getColumnPrivileges(catalog, schema, table, columnNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getColumns(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getColumns(catalog, schemaPattern, tableNamePattern,
				columnNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getConnection();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getCrossReference(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getCrossReference(primaryCatalog, primarySchema, primaryTable,
				foreignCatalog, foreignSchema, foreignTable);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDatabaseMajorVersion()
	 */
	public int getDatabaseMajorVersion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDatabaseMajorVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDatabaseMinorVersion()
	 */
	public int getDatabaseMinorVersion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDatabaseMinorVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDatabaseProductName()
	 */
	public String getDatabaseProductName() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDatabaseProductName();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDatabaseProductVersion()
	 */
	public String getDatabaseProductVersion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDatabaseProductVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDefaultTransactionIsolation()
	 */
	public int getDefaultTransactionIsolation() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDefaultTransactionIsolation();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDriverMajorVersion()
	 */
	public int getDriverMajorVersion() {
		
		return this.getWrappedDatabaseMetaData().getDriverMajorVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDriverMinorVersion()
	 */
	public int getDriverMinorVersion() {
		
		return this.getWrappedDatabaseMetaData().getDriverMinorVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDriverName()
	 */
	public String getDriverName() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDriverName();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getDriverVersion()
	 */
	public String getDriverVersion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getDriverVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getExportedKeys(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getExportedKeys(catalog, schema, table);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getExtraNameCharacters()
	 */
	public String getExtraNameCharacters() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getExtraNameCharacters();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getIdentifierQuoteString()
	 */
	public String getIdentifierQuoteString() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getIdentifierQuoteString();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getImportedKeys(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getImportedKeys(catalog, schema, table);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getIndexInfo(java.lang.String, java.lang.String, java.lang.String, boolean, boolean)
	 */
	public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getIndexInfo(catalog, schema, table, unique, approximate);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getJDBCMajorVersion()
	 */
	public int getJDBCMajorVersion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getJDBCMajorVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getJDBCMinorVersion()
	 */
	public int getJDBCMinorVersion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getJDBCMinorVersion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxBinaryLiteralLength()
	 */
	public int getMaxBinaryLiteralLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxBinaryLiteralLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxCatalogNameLength()
	 */
	public int getMaxCatalogNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxCatalogNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxCharLiteralLength()
	 */
	public int getMaxCharLiteralLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxCharLiteralLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxColumnNameLength()
	 */
	public int getMaxColumnNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxColumnNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxColumnsInGroupBy()
	 */
	public int getMaxColumnsInGroupBy() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxColumnsInGroupBy();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxColumnsInIndex()
	 */
	public int getMaxColumnsInIndex() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxColumnsInIndex();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxColumnsInOrderBy()
	 */
	public int getMaxColumnsInOrderBy() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxColumnsInOrderBy();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxColumnsInSelect()
	 */
	public int getMaxColumnsInSelect() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxColumnsInSelect();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxColumnsInTable()
	 */
	public int getMaxColumnsInTable() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxColumnsInTable();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxConnections()
	 */
	public int getMaxConnections() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxConnections();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxCursorNameLength()
	 */
	public int getMaxCursorNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxCursorNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxIndexLength()
	 */
	public int getMaxIndexLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxIndexLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxProcedureNameLength()
	 */
	public int getMaxProcedureNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxProcedureNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxRowSize()
	 */
	public int getMaxRowSize() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxRowSize();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxSchemaNameLength()
	 */
	public int getMaxSchemaNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxSchemaNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxStatementLength()
	 */
	public int getMaxStatementLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxStatementLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxStatements()
	 */
	public int getMaxStatements() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxStatements();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxTableNameLength()
	 */
	public int getMaxTableNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxTableNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxTablesInSelect()
	 */
	public int getMaxTablesInSelect() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxTablesInSelect();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getMaxUserNameLength()
	 */
	public int getMaxUserNameLength() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getMaxUserNameLength();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getNumericFunctions()
	 */
	public String getNumericFunctions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getNumericFunctions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getPrimaryKeys(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getPrimaryKeys(catalog, schema, table);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getProcedureColumns(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getProcedureColumns(catalog, schemaPattern, procedureNamePattern,
				columnNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getProcedures(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getProcedures(catalog, schemaPattern, procedureNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getProcedureTerm()
	 */
	public String getProcedureTerm() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getProcedureTerm();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getResultSetHoldability()
	 */
	public int getResultSetHoldability() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getResultSetHoldability();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSchemas()
	 */
	public ResultSet getSchemas() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSchemas();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSchemaTerm()
	 */
	public String getSchemaTerm() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSchemaTerm();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSearchStringEscape()
	 */
	public String getSearchStringEscape() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSearchStringEscape();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSQLKeywords()
	 */
	public String getSQLKeywords() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSQLKeywords();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSQLStateType()
	 */
	public int getSQLStateType() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSQLStateType();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getStringFunctions()
	 */
	public String getStringFunctions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getStringFunctions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSuperTables(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSuperTables(catalog, schemaPattern, tableNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSuperTypes(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSuperTypes(catalog, schemaPattern, typeNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getSystemFunctions()
	 */
	public String getSystemFunctions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getSystemFunctions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getTablePrivileges(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getTablePrivileges(catalog, schemaPattern, tableNamePattern);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getTables(java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getTables(catalog, schemaPattern, tableNamePattern, types);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getTableTypes()
	 */
	public ResultSet getTableTypes() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getTableTypes();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getTimeDateFunctions()
	 */
	public String getTimeDateFunctions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getTimeDateFunctions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getTypeInfo()
	 */
	public ResultSet getTypeInfo() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getTypeInfo();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getUDTs(java.lang.String, java.lang.String, java.lang.String, int[])
	 */
	public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getUDTs(catalog, schemaPattern, typeNamePattern, types);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getURL()
	 */
	public String getURL() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getURL();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getUserName()
	 */
	public String getUserName() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getUserName();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#getVersionColumns(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().getVersionColumns(catalog, schema, table);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#insertsAreDetected(int)
	 */
	public boolean insertsAreDetected(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().insertsAreDetected(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#isCatalogAtStart()
	 */
	public boolean isCatalogAtStart() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().isCatalogAtStart();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#isReadOnly()
	 */
	public boolean isReadOnly() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().isReadOnly();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#locatorsUpdateCopy()
	 */
	public boolean locatorsUpdateCopy() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().locatorsUpdateCopy();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#nullPlusNonNullIsNull()
	 */
	public boolean nullPlusNonNullIsNull() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().nullPlusNonNullIsNull();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#nullsAreSortedAtEnd()
	 */
	public boolean nullsAreSortedAtEnd() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().nullsAreSortedAtEnd();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#nullsAreSortedAtStart()
	 */
	public boolean nullsAreSortedAtStart() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().nullsAreSortedAtStart();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#nullsAreSortedHigh()
	 */
	public boolean nullsAreSortedHigh() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().nullsAreSortedHigh();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#nullsAreSortedLow()
	 */
	public boolean nullsAreSortedLow() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().nullsAreSortedLow();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#othersDeletesAreVisible(int)
	 */
	public boolean othersDeletesAreVisible(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().othersDeletesAreVisible(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#othersInsertsAreVisible(int)
	 */
	public boolean othersInsertsAreVisible(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().othersInsertsAreVisible(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#othersUpdatesAreVisible(int)
	 */
	public boolean othersUpdatesAreVisible(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().othersUpdatesAreVisible(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#ownDeletesAreVisible(int)
	 */
	public boolean ownDeletesAreVisible(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().ownDeletesAreVisible(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#ownInsertsAreVisible(int)
	 */
	public boolean ownInsertsAreVisible(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().ownInsertsAreVisible(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#ownUpdatesAreVisible(int)
	 */
	public boolean ownUpdatesAreVisible(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().ownUpdatesAreVisible(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#storesLowerCaseIdentifiers()
	 */
	public boolean storesLowerCaseIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().storesLowerCaseIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#storesLowerCaseQuotedIdentifiers()
	 */
	public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().storesLowerCaseQuotedIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#storesMixedCaseIdentifiers()
	 */
	public boolean storesMixedCaseIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().storesMixedCaseIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#storesMixedCaseQuotedIdentifiers()
	 */
	public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().storesMixedCaseQuotedIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#storesUpperCaseIdentifiers()
	 */
	public boolean storesUpperCaseIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().storesUpperCaseIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#storesUpperCaseQuotedIdentifiers()
	 */
	public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().storesUpperCaseQuotedIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsAlterTableWithAddColumn()
	 */
	public boolean supportsAlterTableWithAddColumn() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsAlterTableWithAddColumn();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsAlterTableWithDropColumn()
	 */
	public boolean supportsAlterTableWithDropColumn() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsAlterTableWithDropColumn();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsANSI92EntryLevelSQL()
	 */
	public boolean supportsANSI92EntryLevelSQL() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsANSI92EntryLevelSQL();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsANSI92FullSQL()
	 */
	public boolean supportsANSI92FullSQL() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsANSI92FullSQL();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsANSI92IntermediateSQL()
	 */
	public boolean supportsANSI92IntermediateSQL() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsANSI92IntermediateSQL();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsBatchUpdates()
	 */
	public boolean supportsBatchUpdates() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsBatchUpdates();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCatalogsInDataManipulation()
	 */
	public boolean supportsCatalogsInDataManipulation() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCatalogsInDataManipulation();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCatalogsInIndexDefinitions()
	 */
	public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCatalogsInIndexDefinitions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCatalogsInPrivilegeDefinitions()
	 */
	public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCatalogsInPrivilegeDefinitions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCatalogsInProcedureCalls()
	 */
	public boolean supportsCatalogsInProcedureCalls() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCatalogsInProcedureCalls();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCatalogsInTableDefinitions()
	 */
	public boolean supportsCatalogsInTableDefinitions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCatalogsInTableDefinitions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsColumnAliasing()
	 */
	public boolean supportsColumnAliasing() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsColumnAliasing();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsConvert()
	 */
	public boolean supportsConvert() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsConvert();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsConvert(int, int)
	 */
	public boolean supportsConvert(int fromType, int toType) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsConvert(fromType, toType);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCoreSQLGrammar()
	 */
	public boolean supportsCoreSQLGrammar() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCoreSQLGrammar();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsCorrelatedSubqueries()
	 */
	public boolean supportsCorrelatedSubqueries() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsCorrelatedSubqueries();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsDataDefinitionAndDataManipulationTransactions()
	 */
	public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsDataDefinitionAndDataManipulationTransactions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsDataManipulationTransactionsOnly()
	 */
	public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsDataManipulationTransactionsOnly();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsDifferentTableCorrelationNames()
	 */
	public boolean supportsDifferentTableCorrelationNames() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsDifferentTableCorrelationNames();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsExpressionsInOrderBy()
	 */
	public boolean supportsExpressionsInOrderBy() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsExpressionsInOrderBy();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsExtendedSQLGrammar()
	 */
	public boolean supportsExtendedSQLGrammar() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsExtendedSQLGrammar();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsFullOuterJoins()
	 */
	public boolean supportsFullOuterJoins() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsFullOuterJoins();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsGetGeneratedKeys()
	 */
	public boolean supportsGetGeneratedKeys() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsGetGeneratedKeys();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsGroupBy()
	 */
	public boolean supportsGroupBy() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsGroupBy();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsGroupByBeyondSelect()
	 */
	public boolean supportsGroupByBeyondSelect() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsGroupByBeyondSelect();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsGroupByUnrelated()
	 */
	public boolean supportsGroupByUnrelated() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsGroupByUnrelated();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsIntegrityEnhancementFacility()
	 */
	public boolean supportsIntegrityEnhancementFacility() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsIntegrityEnhancementFacility();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsLikeEscapeClause()
	 */
	public boolean supportsLikeEscapeClause() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsLikeEscapeClause();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsLimitedOuterJoins()
	 */
	public boolean supportsLimitedOuterJoins() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsLimitedOuterJoins();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsMinimumSQLGrammar()
	 */
	public boolean supportsMinimumSQLGrammar() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsMinimumSQLGrammar();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsMixedCaseIdentifiers()
	 */
	public boolean supportsMixedCaseIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsMixedCaseIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsMixedCaseQuotedIdentifiers()
	 */
	public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsMixedCaseQuotedIdentifiers();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsMultipleOpenResults()
	 */
	public boolean supportsMultipleOpenResults() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsMultipleOpenResults();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsMultipleResultSets()
	 */
	public boolean supportsMultipleResultSets() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsMultipleResultSets();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsMultipleTransactions()
	 */
	public boolean supportsMultipleTransactions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsMultipleTransactions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsNamedParameters()
	 */
	public boolean supportsNamedParameters() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsNamedParameters();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsNonNullableColumns()
	 */
	public boolean supportsNonNullableColumns() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsNonNullableColumns();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsOpenCursorsAcrossCommit()
	 */
	public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsOpenCursorsAcrossCommit();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsOpenCursorsAcrossRollback()
	 */
	public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsOpenCursorsAcrossRollback();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsOpenStatementsAcrossCommit()
	 */
	public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsOpenStatementsAcrossCommit();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsOpenStatementsAcrossRollback()
	 */
	public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsOpenStatementsAcrossRollback();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsOrderByUnrelated()
	 */
	public boolean supportsOrderByUnrelated() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsOrderByUnrelated();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsOuterJoins()
	 */
	public boolean supportsOuterJoins() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsOuterJoins();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsPositionedDelete()
	 */
	public boolean supportsPositionedDelete() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsPositionedDelete();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsPositionedUpdate()
	 */
	public boolean supportsPositionedUpdate() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsPositionedUpdate();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsResultSetConcurrency(int, int)
	 */
	public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsResultSetConcurrency(type, concurrency);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsResultSetHoldability(int)
	 */
	public boolean supportsResultSetHoldability(int holdability) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsResultSetHoldability(holdability);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsResultSetType(int)
	 */
	public boolean supportsResultSetType(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsResultSetType(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSavepoints()
	 */
	public boolean supportsSavepoints() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSavepoints();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSchemasInDataManipulation()
	 */
	public boolean supportsSchemasInDataManipulation() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSchemasInDataManipulation();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSchemasInIndexDefinitions()
	 */
	public boolean supportsSchemasInIndexDefinitions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSchemasInIndexDefinitions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSchemasInPrivilegeDefinitions()
	 */
	public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSchemasInPrivilegeDefinitions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSchemasInProcedureCalls()
	 */
	public boolean supportsSchemasInProcedureCalls() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSchemasInProcedureCalls();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSchemasInTableDefinitions()
	 */
	public boolean supportsSchemasInTableDefinitions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSchemasInTableDefinitions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSelectForUpdate()
	 */
	public boolean supportsSelectForUpdate() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSelectForUpdate();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsStatementPooling()
	 */
	public boolean supportsStatementPooling() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsStatementPooling();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsStoredProcedures()
	 */
	public boolean supportsStoredProcedures() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsStoredProcedures();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSubqueriesInComparisons()
	 */
	public boolean supportsSubqueriesInComparisons() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSubqueriesInComparisons();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSubqueriesInExists()
	 */
	public boolean supportsSubqueriesInExists() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSubqueriesInExists();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSubqueriesInIns()
	 */
	public boolean supportsSubqueriesInIns() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSubqueriesInIns();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsSubqueriesInQuantifieds()
	 */
	public boolean supportsSubqueriesInQuantifieds() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsSubqueriesInQuantifieds();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsTableCorrelationNames()
	 */
	public boolean supportsTableCorrelationNames() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsTableCorrelationNames();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsTransactionIsolationLevel(int)
	 */
	public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsTransactionIsolationLevel(level);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsTransactions()
	 */
	public boolean supportsTransactions() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsTransactions();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsUnion()
	 */
	public boolean supportsUnion() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsUnion();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#supportsUnionAll()
	 */
	public boolean supportsUnionAll() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().supportsUnionAll();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#updatesAreDetected(int)
	 */
	public boolean updatesAreDetected(int type) throws SQLException {
		
		return this.getWrappedDatabaseMetaData().updatesAreDetected(type);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#usesLocalFilePerTable()
	 */
	public boolean usesLocalFilePerTable() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().usesLocalFilePerTable();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.sql.Test#usesLocalFiles()
	 */
	public boolean usesLocalFiles() throws SQLException {
		
		return this.getWrappedDatabaseMetaData().usesLocalFiles();
	}

	/**
	 * @return the wrappedDatabaseMetaData
	 */
	public DatabaseMetaData getWrappedDatabaseMetaData() {
		return wrappedDatabaseMetaData;
	}

	/**
	 * @param wrappedDatabaseMetaData the wrappedDatabaseMetaData to set
	 */
	public void setWrappedDatabaseMetaData(DatabaseMetaData wrappedDatabaseMetaData) {
		this.wrappedDatabaseMetaData = wrappedDatabaseMetaData;
	}

	
	
}
