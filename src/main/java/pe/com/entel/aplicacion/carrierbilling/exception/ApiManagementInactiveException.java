package pe.com.entel.aplicacion.carrierbilling.exception;

import pe.com.entel.aplicacion.carrierbilling.domain.ApiManagementError;

public class ApiManagementInactiveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ApiManagementError error;
	
	public ApiManagementInactiveException(ApiManagementError error) {
		super(error.getDescripcionError());
		this.error = error;
	}
	
	public ApiManagementError getError() {
		return error;
	}

	public void setError(ApiManagementError error) {
		this.error = error;
	}
}
