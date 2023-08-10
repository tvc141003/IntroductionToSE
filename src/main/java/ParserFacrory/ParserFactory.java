package ParserFacrory;

import java.util.Map;

public class ParserFactory {
	private static Map<String, IParsable> container;
	
	private static ParserFactory INSTANCE ;
	private ParserFactory() {
		container = null;
	}

	public static ParserFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ParserFactory();
			
		}
		return INSTANCE;
	}
	
	public static void register(IParsable parser) {
		container.put(parser.toString(), parser);
	}
	
	public static IParsable create(String type) {
		return container.get(type);
	}
}
