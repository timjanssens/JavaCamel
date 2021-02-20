package example3;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;import org.apache.camel.support.ProcessorEndpoint;

public class ProduceAndConsumeExample {

	public static void main(String[] args) throws Exception {
		
		
		
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
				
				from("direct:start")
				.process(new Processor() {
					
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						
						System.out.println("I am the processor...");
						
						
					}
				})
				.to("seda:end");
				
				
				
			}
		});
		
		context.start();
		
		ProducerTemplate producerTemplate  = context.createProducerTemplate();
		
		producerTemplate.sendBody("direct:start", "Hello Everyone");
		
		
		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		String message = consumerTemplate.receiveBody("seda:end" , String.class);
		
		System.out.println(message);
		
	}
	
	
}
