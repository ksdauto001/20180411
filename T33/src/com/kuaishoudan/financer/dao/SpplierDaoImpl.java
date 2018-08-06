package com.kuaishoudan.financer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaishoudan.financer.bean.Finance;
import com.kuaishoudan.financer.bean.FinanceAdvence;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class SpplierDaoImpl {

	public static void main(String[] args) {

		KSDCase ksd = RandomValue.getRandom();
		;
		List<String>fffs=getSuplier(ksd);
		for(int i=0;i<fffs.size();i++){
			System.out.println(fffs.get(i));
		}
	}

	public static Map<String, String> getCustomer(KSDCase ksd) {
		String ff = "";

		String sql = " select * from tb_customer where id_num=?; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		Map<String, String> map = new HashMap<String, String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				map.put("name", rs.getString("name"));
				map.put("status", rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("id_type", rs.getString("id_type"));
				map.put("address", rs.getString("address"));
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			// util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}
	public static List<String> getSuplier(KSDCase ksd) {
		String ff = "";
		List<String> list  = new ArrayList<String>();
		String sql = " SELECT ts.id,ts.`name`,ts.supplier_code FROM tb_supplier ts ,tb_employee e WHERE FIND_IN_SET(e.id, ts.follow_people) and ts.flag=3 and  e.`name`=? and e.position_desc like '%测试%' order by ts.start_with ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
 
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
				pstmt.setString(1, ksd.getLoginname());
		
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(3));
				
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			// util.closeConn(conn);
		}

		return list;
	}

}