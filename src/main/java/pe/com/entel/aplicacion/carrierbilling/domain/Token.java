package pe.com.entel.aplicacion.carrierbilling.domain;

public class Token {
	private String acces_token;
	private String token_type;
	private String expires_in;
	private String scope;
	
	public String getAcces_token() {
		return acces_token;
	}
	public void setAcces_token(String acces_token) {
		this.acces_token = acces_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
