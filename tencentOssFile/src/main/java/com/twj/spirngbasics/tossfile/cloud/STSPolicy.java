package com.twj.spirngbasics.tossfile.cloud;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author bradyxiao
 * remove principal 
 * @since 3.0.3
 */
public class STSPolicy {
	
	private List<Scope> scopes = new ArrayList<Scope>();
	
	public STSPolicy() {
		
	}
	
	public void addScope(List<Scope> scopes) {
		if(scopes != null) {
			for(Scope scope : scopes) {
				this.scopes.add(scope);
			}
		}
	}
	
	public void addScope(Scope scope) {
		this.scopes.add(scope);
	}
	
	private JSONObject createElement(Scope scope) {
		JSONObject element = new JSONObject();
		
		JSONArray actions = new JSONArray();
		actions.add(scope.getAction());
		element.put("action", actions);
		
		element.put("effect", scope.getEffect());
		
		JSONArray resources = new JSONArray();
		resources.add(scope.getResource());
		element.put("resource", resources);
		
		return element;
	}
	
	@Override
	public String toString() {
		JSONObject policy = new JSONObject();
    	policy.put("version", "2.0");
    	JSONArray statement = new JSONArray();
    	if(scopes.size() > 0) {
    		for(Scope scope : scopes) {
    			statement.add(createElement(scope));
    		}
    		policy.put("statement", statement);
    	}
    	return policy.toString();
	}
}
