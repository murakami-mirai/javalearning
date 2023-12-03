package javalearning.core.listener;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public class ErrorListener implements DiagnosticListener<JavaFileObject> {

	@Override
	public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
		System.out.println("▼report start");
		System.out.println("errcode：" + diagnostic.getCode());
		System.out.println("line   ：" + diagnostic.getLineNumber());
		System.out.println("column ：" + diagnostic.getColumnNumber());
		System.out.println("message：" + diagnostic.getMessage(null));
		//System.out.println(diagnostic.toString());
		System.out.println("▲report end");
	}

}
