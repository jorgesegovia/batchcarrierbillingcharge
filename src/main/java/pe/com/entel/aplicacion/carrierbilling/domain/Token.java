package pe.com.entel.aplicacion.carrierbilling.domain;

public class Token {
	private String access_token;
	private String token_type;
	private int expires_in;
	private String scope;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String acces_token) {
		this.access_token = acces_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
