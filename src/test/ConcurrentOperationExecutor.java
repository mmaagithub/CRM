package test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.dao.OptimisticLockingFailureException;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class ConcurrentOperationExecutor implements Ordered {

	private static final Logger logger = LoggerFactory.getLogger(ConcurrentOperationExecutor.class);

	private static final int DEFAULT_MAX_RETRIES = 5;
	private int maxRetries = DEFAULT_MAX_RETRIES;
	private int order = 100;

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	@Override
	public int getOrder() {
		return 0;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
		int numAttempts = 0;
		OptimisticLockingFailureException lockFailureException;
		do {
			numAttempts++;
			try {
				return pjp.proceed();
			} catch (OptimisticLockingFailureException ex) {
				lockFailureException = ex;
				logger.debug("caught lock failure exception, attempts: "+ numAttempts);
			}
		} while (numAttempts <= this.maxRetries);
		throw lockFailureException;
	}

}
