package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.business.Element;

public class ElementDAOMySql implements IElementDAO{
	private BDMySql bdMysql = BDMySql.getInstance();
	
	@Override
	public void save(Element element) throws Exception {
		String sql = new String(); 
		int id_parent_element = this.getIdParentElement(element.getParent_element());
		sql = "INSERT INTO element (name, id_parent_element) VALUES ('" + element.getName() +"', '"+id_parent_element+"')";
		bdMysql.executarSQL(sql);
	}

	public int getIdParentElement(Element element){
		int id_parent_element = 1;
		String sql = new String();
		sql = "select id_element from element where name = '" +element.getName()+"'";
		ResultSet rs = bdMysql.executarBuscaSQL(sql);
		if (rs == null) {
			id_parent_element = 1;
		} else
			try {
				if(rs.next()) {
					id_parent_element = rs.getInt("id_element");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return id_parent_element;
	}
	
}
