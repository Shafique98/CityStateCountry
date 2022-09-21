package com.shafiq.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shafiq.pojo.City;
import com.shafiq.pojo.Country;
import com.shafiq.pojo.State;

public class CountryStateLogic {
	Connection con;
    PreparedStatement pst;
    String query;
    ResultSet rs;
    
    public CountryStateLogic(Connection con) {
        this.con = con;
    }
    
    public List<Country> getAllCountry(){
    	List<Country> list = new ArrayList<>();
    	try {
    		query = "SELECT * FROM COUNTRYLIST";
    		pst = this.con.prepareStatement(query);
    		rs = pst.executeQuery();
    		while(rs.next()) {
    			Country country = new Country();
    			country.setId(rs.getInt("id"));

    			country.setName(rs.getString("name"));
    			list.add(country);
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    public List<State> getStateByCountryId(int countryId){
    	List<State> list = new ArrayList<>();
    	try {
    		query = "SELECT * FROM STATELIST WHERE C_ID=?";
    		pst = this.con.prepareStatement(query);
    		pst.setInt(1, countryId);
    		rs = pst.executeQuery();
    		while(rs.next()) {
    			
    			State state = new State();
    			state.setId(rs.getInt("id"));
    			state.setCountryId(rs.getInt("C_ID"));
    			state.setName(rs.getString("NAME"));
    			list.add(state);
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    public List<City> getCityByStateId(int stateId){
    	List<City> list = new ArrayList<>();
    	try {
    		query = "SELECT * FROM CITYLIST WHERE S_ID=?";
    		pst = this.con.prepareStatement(query);
    		pst.setInt(1, stateId);
    		rs = pst.executeQuery();
    		while(rs.next()) {
    			
    			City city = new City();
    			city.setId(rs.getInt("ID"));
    			city.setStateId(rs.getInt("S_ID"));
    			city.setName(rs.getString("NAME"));
    		    list.add(city);
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
}
