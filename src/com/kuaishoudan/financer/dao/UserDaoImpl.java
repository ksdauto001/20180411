package com.kuaishoudan.financer.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kuaishoudan.financer.bean.Finance;
import com.kuaishoudan.financer.bean.FinanceAdvence;
import com.kuaishoudan.financer.util.DBUtil;





public class UserDaoImpl{
	
	
	public static void main(String[] args) {
		

	/*	Finance f=UserDaoImpl.getFinance("71238");
		System.out.println(f.getFinanceid()+f.getUsername()+f.getBrandid()+f.getRate()+f.getProductname());*/
		//FinanceAdvence f=UserDaoImpl.getAdvence("1062474");
		//System.out.println(f.getFinanceid()+f.getUsername());
		int a=gethave_system("易鑫融资租赁");
		System.out.println(a);
	}
	public static Finance getFinance(String id) {

		Finance f=null;
		String sql = " select  * from tb_finance where customer_id=?  order by allot_time desc; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				f=new Finance();
				f.setFinanceid(rs.getInt("id"));
				f.setUsername( rs.getString("user_name"));
				f.setPhone(rs.getString("phone"));
				f.setBrandid(rs.getInt("brand_id"));
				f.setBrandname(rs.getString("brand_name"));
				f.setSeriesid(rs.getInt("series_id"));
				f.setSeriesname(rs.getString("series_name"));
				f.setCarprice(rs.getDouble("car_price"));
				f.setProductid(rs.getInt("product_id"));
				f.setProductname(rs.getString("product_name"));
				f.setLoanamount(rs.getDouble("loan_amount"));
				f.setPayperiods(rs.getInt("pay_periods"));
				f.setCompactno(rs.getLong("compact_no"));
				f.setStatues(rs.getInt("status"));
				f.setSupplierid(rs.getInt("supplier_id"));
				f.setSuppliername(rs.getString("supplier_name"));
				f.setRemark(rs.getString("remark"));
				f.setCityid(rs.getInt("city_id"));
				f.setReason(rs.getString("reason"));
				f.setOrganizationid(rs.getInt("organization_id"));
				f.setOrganizationname(rs.getString("organization_name"));
				f.setCustomid(rs.getInt("customer_id"));
				f.setRate(rs.getDouble("rate"));
				f.setPurchasetax(rs.getDouble("purchase_tax"));
				f.setGpscharge(rs.getDouble("gps_charge"));
				f.setInsurance(rs.getDouble("insurance"));
				f.setServicecharge(rs.getDouble("service_charge"));
			System.out.println(f.getProductname()+"@");;
			
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return f;
	}
	public static FinanceAdvence getAdvence(String finance_id) {

		FinanceAdvence f=null;
		String sql = " select *from tb_finance_advance where  finance_id= ?  order by id desc; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, finance_id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				f=new FinanceAdvence();
				f.setPledgecity(rs.getString("pledge_city"));
				f.setPledgecityid(rs.getInt("pledge_city_id"));
				f.setFinanceid(rs.getInt("finance_id"));
				f.setUsername( rs.getString("user_name"));
				f.setTotalcharge(rs.getDouble("total_charge"));
			
				f.setSuppliername(rs.getString("supplier_name"));
				f.setPayname(rs.getString("pay_name"));
				f.setPayaccount(rs.getString("pay_account"));
				f.setPayopenbank(rs.getString("pay_open_bank"));
				f.setPurchasetax(rs.getDouble("purchase_tax"));
				f.setGpscharge(rs.getDouble("gps_charge"));
				f.setInsurance(rs.getDouble("insurance"));
				f.setServicecharge(rs.getDouble("service_charge"));
			
			
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return f;
	}
	public static int gethave_system(String organization_name) {
		int have_system=1;
	
		String sql = " select max( have_system) from tb_organization where name =? and company_id=1000 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, organization_name);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				have_system=	rs.getInt(1);
			
			
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return have_system;
	}

}