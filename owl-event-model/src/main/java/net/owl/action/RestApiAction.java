package net.owl.action;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.owl.action.exception.ActionException;
import net.owl.action.exception.WebApiInvocationException;
import net.owl.log.EventLog;
import net.owl.log.GenericLogWriter;
import net.owl.log.LogWriter;
import net.toolab.http.HttpRequesterFactory;
import net.toolab.http.context.GetRequestContext;
import net.toolab.http.context.HttpRequestContext;
import net.toolab.http.context.PostRequestContext;
import net.toolab.http.exception.HttpRequestException;
import net.toolab.http.exception.RequesterCreationException;
import net.toolab.http.exception.UnsupportedRequestException;
import net.toolab.utils.ReflectionUtils;
import net.toolab.utils.exception.NotAPojoException;

import org.apache.http.client.HttpResponseException;

public class RestApiAction<P> extends AbstractAction<P> {
	
	private String apiUri;
	private String method;
	
	public RestApiAction(String apiUri) {
		this(apiUri, "get");
	}
	
	public RestApiAction(String apiUri, String method) {
		super("rest api action");
		
		this.apiUri = apiUri;
		this.method = method.toLowerCase();
	}

	public void setApiUri(String apiUri) {
		this.apiUri = apiUri;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	protected LogWriter<? extends EventLog> process(P data) {
		log.debug("invoke web api : [{}]", getName());
		
		if (data == null) {
			throw new ActionException("source data is empty!!");
		}
		
		RestApiRequest meta = null;
		
		try {
			meta = new RestApiRequest(apiUri, data);
			
			HttpRequestContext context = getRequestContext(meta.uri, meta.source);
			
			String message = HttpRequesterFactory.getRequester(context).request();
			log.debug("response : [{}]", message);
			
			StringBuilder action = new StringBuilder();
			action.append("action name : ").append(getName()).append(" / ")
				.append("method : ").append(method).append(" / ")
				.append("api : ").append(meta.uri).append("\n")
				.append("response : ").append(message);
			
			return new GenericLogWriter().action(action.toString());
		} catch (URISyntaxException e) {
			throw new WebApiInvocationException("illegal api uri..", meta.uri, e);
		} catch (HttpRequestException e) {
			throw new WebApiInvocationException("fail to http request..", meta.uri, e);
		} catch (HttpResponseException e) {
			throw new WebApiInvocationException("could not response message from api server.", meta.uri, e);
		} catch (RequesterCreationException e) {
			throw new WebApiInvocationException("invalid request data.", meta.uri, e);
		} catch (UnsupportedRequestException e) {
			throw new WebApiInvocationException("it's not support method!! " + method, meta.uri, e);
		} catch (WebApiInvocationException e) {
			throw e;
		} catch (Exception e) {
			throw new WebApiInvocationException(e);
		}
	}

	private HttpRequestContext getRequestContext(String uri, Map<String, Object> parameter) throws URISyntaxException {
		if ("post".equals(method)) {
			HttpRequestContext context = new PostRequestContext(uri)
					.setConnectionTimeout(3000)
					.addFormParameters(parameter);
			
			return context;
		} else if ("get".equals(method)) {
			HttpRequestContext context = new GetRequestContext(uri)
					.setConnectionTimeout(3000)
					.addQueries(parameter);
			
			return context;
		} else {
			throw new WebApiInvocationException("it's not support method!! (" + method.toUpperCase() +")", apiUri);
		}
	}
	
	private class RestApiRequest {
		private String uri;
		private Map<String, Object> source;
		
		RestApiRequest(String uri, Object param) throws NotAPojoException {
			this.uri = uri;
			extract(param);
		}
		
		@SuppressWarnings("unchecked")
		private void extract(Object data) throws NotAPojoException {
			Pattern path = Pattern.compile("\\{[a-zA-Z0-9}]*\\}");
			
			if (data instanceof Map) {
				source = new HashMap<>(((Map<String, Object>)data));
			} else {			
				source = new HashMap<>(ReflectionUtils.asMap(data));
			}
			
			Matcher matcher = path.matcher(this.uri);
			
			StringBuffer uriBuffer = new StringBuffer();
			while (matcher.find()) {
				String key = matcher.group().replaceAll("[\\{\\}]", "");
				matcher.appendReplacement(uriBuffer, String.valueOf(source.get(key)));
				source.remove(key);
			}
			
			this.uri = uriBuffer.toString();
		}
	}
}