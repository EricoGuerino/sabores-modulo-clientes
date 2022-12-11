package com.tcc.saboresmoduloclientes.resources.exceptions;

import java.io.Serializable;
import java.util.Date;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 9153829516951701892L;
	private Integer status;
	private String msg;
	private Date dtHrError;
	
	public StandardError() {}
	public StandardError(Integer status, String msg, Date dtHrError) {
		setStatus(status);
		setMsg(msg);
		setDtHrError(dtHrError);
	}
	
	public Integer getStatus() 	{ return status; 	}
	public String getMsg() 		{ return msg; 		}
	public Date getDtHrError() 	{ return dtHrError; }

	public void setStatus(Integer status) 		{ this.status = status; 		}
	public void setMsg(String msg) 				{ this.msg = msg; 				}
	public void setDtHrError(Date dtHrError) 	{ this.dtHrError = dtHrError; 	}
	
}
