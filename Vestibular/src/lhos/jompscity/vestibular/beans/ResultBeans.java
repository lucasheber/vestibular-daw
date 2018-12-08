package lhos.jompscity.vestibular.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@ViewScoped
@ManagedBean
public class ResultBeans {

	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
