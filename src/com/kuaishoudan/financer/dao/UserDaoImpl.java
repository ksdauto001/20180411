package com.kuaishoudan.financer.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.kuaishoudan.financer.bean.Finance;
import com.kuaishoudan.financer.bean.FinanceAdvence;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;





public class UserDaoImpl{
	
	
	public static void main(String[] args) {
		

	/*	Finance f=UserDaoImpl.getFinance("71238");
		System.out.println(f.getFinanceid()+f.getUsername()+f.getBrandid()+f.getRate()+f.getProductname());*/
		//FinanceAdvence f=UserDaoImpl.getAdvence("1062474");
		//System.out.println(f.getFinanceid()+f.getUsername());
/*		int a=gethave_system("易鑫融资租赁");
		System.out.println(a);*/
		  KSDCase ksd=RandomValue.getRandom();;
		  ksd.setIdentitytype(1);
		 ksd.setIdentitynum("620921195909158870"); 
		 Map<String,String> map= getCustomer(ksd);
		 
		 System.out.println(map.get("phone")+" "+map.get("address")+map.get("status"));
		 
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

			while (rs.next()) {
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

			while (rs.next()) {
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
	public static  Map<String,String> getCustomer(KSDCase ksd) {
		String ff="";
	
		String sql = " select * from tb_customer where id_num=?; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		  Map<String,String> map=new HashMap<String,String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(ksd.getIdentitytype()==1){
			pstmt.setString(1,ksd.getIdentitynum() );
			}else if(ksd.getIdentitytype()==2){
				pstmt.setString(1, ksd.getJgid());
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				map.put("name",	rs.getString("name"));
				map.put("status",	rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("id_type",	rs.getString("id_type"));
				map.put("address",	rs.getString("address"));
			//	System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}
	public static  Map<String,String> getFinance(KSDCase ksd) {
		String ff="";
	       DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		String sql = " select * from tb_finance where customer_id=(select id from tb_customer where id_num=?) order by id desc  limit 1 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		  Map<String,String> map=new HashMap<String,String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			if(ksd.getIdentitytype()==1){
				pstmt.setString(1,ksd.getIdentitynum() );
				}else if(ksd.getIdentitytype()==2){
					pstmt.setString(1, ksd.getJgid());
				}
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("name",	rs.getString("user_name"));
				map.put("status",	rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("car_type",	rs.getString("car_type"));
				map.put("brand_name",	rs.getString("brand_name"));
				map.put("series_name",	rs.getString("series_name"));
				map.put("product_name",	rs.getString("product_name"));
				map.put("car_price",""+rs.getDouble("car_price"));// decimalFormat.format(rs.getDouble("car_price"))
				System.out.println("car_price"+rs.getString("car_price"));
				System.out.println("car_price"+rs.getDouble("car_price"));
				map.put("loan_amount",	""+rs.getDouble("loan_amount"));
				map.put("pay_periods",	rs.getString("pay_periods"));
				map.put("supplier_name",	rs.getString("supplier_name"));
				map.put("remark",	rs.getString("remark"));
				map.put("loan_type",	rs.getString("loan_type"));
				if(ksd.getQygr()==2){
				map.put("business_name",rs.getString("business_name"));
				map.put("business_license",	rs.getString("business_license"));
				}
				map.put("rate", rs.getString("rate"));
				map.put("vin", rs.getString("vin"));
				map.put("purchase_tax", ""+rs.getDouble("purchase_tax"));
				map.put("gps_charge", ""+rs.getDouble("gps_charge"));
				map.put("insurance",""+rs.getDouble("insurance"));
				System.out.println("==="+rs.getString("insurance"));
				System.out.println("==="+""+ rs.getDouble("insurance"));
				map.put("service_charge",""+ rs.getDouble("service_charge"));
			//	System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}
	public static  Map<String,String> getAdvance(KSDCase ksd) {
		String ff="";
	       DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		String sql = " select * from tb_finance_advance where finance_id=(select id from tb_finance where customer_id=(select id from tb_customer where id_num=?)order by id desc  limit 1)  ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		  Map<String,String> map=new HashMap<String,String>();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			if(ksd.getIdentitytype()==1){
				pstmt.setString(1,ksd.getIdentitynum() );
				}else if(ksd.getIdentitytype()==2){
					pstmt.setString(1, ksd.getJgid());
				}
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("name",	rs.getString("user_name"));
				map.put("car_type",	rs.getString("car_type"));
				map.put("series_name",	rs.getString("series_name"));
				map.put("product_name",	rs.getString("product_name"));
				map.put("supplier_name",	rs.getString("supplier_name"));
				map.put("vin", rs.getString("vin"));
				map.put("purchase_tax", rs.getString("purchase_tax"));
				map.put("gps_charge", rs.getString("gps_charge"));
				map.put("insurance",rs.getString("insurance"));
				map.put("service_charge", rs.getString("service_charge"));
			//	System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(ff);
		return map;
	}
	public static int getstatus_id(String name) {
		int have_system=1;
	
		String sql = "select id from tb_status where name=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);

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