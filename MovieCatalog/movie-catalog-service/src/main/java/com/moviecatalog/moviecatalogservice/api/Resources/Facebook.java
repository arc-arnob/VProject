package com.moviecatalog.moviecatalogservice.api.Resources;

import com.moviecatalog.moviecatalogservice.api.resource.ApiBinding;



public class Facebook extends ApiBinding {

	private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v2.12";
	
	public Facebook(String accessToken) {
		super(accessToken);
	}
	
	public Profile getProfile() {
		return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me", Profile.class);
    }
}