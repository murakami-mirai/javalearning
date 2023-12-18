package javalearning.core.listener;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public class ErrorListener implements DiagnosticListener<JavaFileObject> {

	@Override
	public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
		System.err.println("▼report start");
		System.err.println("errcode：" + diagnostic.getCode());
		System.err.println("line   ：" + diagnostic.getLineNumber());
		System.err.println("column ：" + diagnostic.getColumnNumber());
		System.err.println("message：" + diagnostic.getMessage(null));
		//System.out.println(diagnostic.toString());
		System.err.println("▲report end");
	}

}
