package tclisten;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

import tclisten.autoconfigure.AjpProtocol;
import tclisten.autoconfigure.AprAjpConnectorProps;
import tclisten.autoconfigure.AprHttpConnectorProps;
import tclisten.autoconfigure.BioAjpConnectorProps;
import tclisten.autoconfigure.BioHttpConnectorProps;
import tclisten.autoconfigure.ConnectorProps;
import tclisten.autoconfigure.DefaultAjpConnectorProps;
import tclisten.autoconfigure.DefaultHttpConnectorProps;
import tclisten.autoconfigure.HttpProtocol;
import tclisten.autoconfigure.Nio2AjpConnectorProps;
import tclisten.autoconfigure.Nio2HttpConnectorProps;
import tclisten.autoconfigure.NioAjpConnectorProps;
import tclisten.autoconfigure.NioHttpConnectorProps;
import tclisten.autoconfigure.TcProps;

public class TomcatEmbeddedServletContainerFactoryConfigurer {

	private TomcatEmbeddedServletContainerFactory tcFactory;
	private TcProps tcProps;
	private boolean apr;
	private boolean primary;
	
	private static Log log = LogFactory.getLog(TomcatEmbeddedServletContainerFactoryConfigurer.class);
			
	/**
	 * Initializing Constructor
	 * @param tcFactory The autoconfigured tomcat factory.
	 * @param tcProps The configuration properties.
	 */
	public TomcatEmbeddedServletContainerFactoryConfigurer(
			TomcatEmbeddedServletContainerFactory tcFactory, TcProps tcProps) {
		this.tcFactory = tcFactory;
		this.tcProps = tcProps;
		this.apr = false;
		this.primary = false;
		this.prepareApr();
	}
	
	public void configureConnectors(){
		boolean haveCustom = false;
		
		if(this.tcProps.getAprHttpConnectors().size() > 0){
			haveCustom = true;
			for(AprHttpConnectorProps cp : this.tcProps.getAprHttpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getBioHttpConnectors().size() > 0){
			haveCustom = true;
			for(BioHttpConnectorProps cp : this.tcProps.getBioHttpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getNioHttpConnectors().size() > 0){
			haveCustom = true;
			for(NioHttpConnectorProps cp : this.tcProps.getNioHttpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getNio2HttpConnectors().size() > 0){
			haveCustom = true;
			for(Nio2HttpConnectorProps cp : this.tcProps.getNio2HttpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getAprAjpConnectors().size() > 0){
			haveCustom = true;
			for(AprAjpConnectorProps cp : this.tcProps.getAprAjpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getBioAjpConnectors().size() > 0){
			haveCustom = true;
			for(BioAjpConnectorProps cp : this.tcProps.getBioAjpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getNioAjpConnectors().size() > 0){
			haveCustom = true;
			for(NioAjpConnectorProps cp : this.tcProps.getNioAjpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(this.tcProps.getNio2AjpConnectors().size() > 0){
			haveCustom = true;
			for(Nio2AjpConnectorProps cp : this.tcProps.getNio2AjpConnectors())
				this.addConnector(cp, cp.getProtocol().toString());
		}
		if(!haveCustom){
			log.info("No customized connectors configured.  Preparing default connectors.");
			this.setDefaultConnectors();
		}
	}
	
	private <T extends ConnectorProps> void addConnector(T connProps, String protocol){
		Connector conn = new Connector(protocol);
		if(primary){
			log.info("Creating additional " + protocol + " connector for " +
					connProps.getAddress() + ":" + connProps.getPort() + ".");
			connProps.getCustomizer().customize(conn);
			this.tcFactory.addAdditionalTomcatConnectors(conn);
			return;
		}
		this.tcFactory.setProtocol(protocol);
		this.tcFactory.setPort(connProps.getPort());
		this.tcFactory.addConnectorCustomizers(connProps.getCustomizer());
		log.info("Resetting primary "+ protocol + " connector for " +
				connProps.getAddress() + ":" + connProps.getPort() + ".");
		this.primary = true;
	}
	
	private void setDefaultConnectors(){
		// The default action is to mimic HTTP/1.1 on 8080 & AJP/1.3 on 8009 connectors.
		// That is, to use APR if available, otherwise use NIO
		DefaultHttpConnectorProps http = new DefaultHttpConnectorProps();
		DefaultAjpConnectorProps ajp = new DefaultAjpConnectorProps();
		this.addConnector(http, HttpProtocol.DEFAULT.toString());
		this.addConnector(ajp, AjpProtocol.DEFAULT.toString());
	}
	
	private void prepareApr(){
		AprLifecycleListener aprLifecycleListener = new AprLifecycleListener();
		if(!AprLifecycleListener.isAprAvailable())
			return;
		this.apr = true;
		aprLifecycleListener.setFIPSMode("Off");
		try {
			aprLifecycleListener.setSSLEngine("On");
		}catch(Exception e){
			// Ignored: APR library was complied without SSL support.
			// Any connectors which utilize SSL will throw an error inside Tomcat's
			// code stack.
		}
		
		if(this.apr)
			this.tcFactory.addContextLifecycleListeners(aprLifecycleListener);
	}
}
