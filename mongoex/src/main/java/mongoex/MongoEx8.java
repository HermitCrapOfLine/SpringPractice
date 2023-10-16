package mongoex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import mongoVO.UserVO;

public class MongoEx8 {
	public static void main(String[] args) {
		
		Bson filter2 = new Document("name", 1);
		
		try(MongoClient client = 
				new MongoClient(new MongoClientURI
						("mongodb://user:1234@localhost:27017/shop"));) {
			
			MongoDatabase database = client.getDatabase("shop");
			
			MongoCollection<Document> collection = database.getCollection("member");
			
			// 프로젝션 사용하는 첫 번째 방법
			Bson proj = new Document("_id", 0)
							.append("name", 1)
							.append("age", 1)
							.append("office", 1);
			
			Bson doc1 = new Document()
					.append("age", "10");
			
			
			
			FindIterable<Document> result = collection.find()
					.projection(proj)
					.sort(filter2)
					.skip(5)
					.limit(10);
			
			// 프로젝션 사용하는 두 번째 방법
//			Bson proj = Projections.include("name", "age");
//			Bson proj = Projections.exclued("office", "phone");
	
// 			Bson proj = Projections.fields(
//					Projections.excludeId(),
//					Projections.include("name", "age")
//			);
			
			
					
			System.out.println("-----------");
			System.out.println(result.first());
			System.out.println(result.first().containsKey("name"));
			System.out.println(result.first().containsValue("Peter John"));
			System.out.println("-----------");
			
			for (Document document : result) {
				System.out.print(document.get("id") + "\t");
				System.out.print(document.get("pw") + "\t");
				System.out.print(document.get("name") + "\t");
				System.out.print(document.get("tel") + "\t");
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", result.first().get("id"));
			map.put("pw", result.first().get("pw"));
			map.put("name", result.first().get("name"));
			map.put("tel", result.first().get("tel"));
			System.out.println("-------------------");
			
			System.out.println(map);
			
			ArrayList<Map> list = new ArrayList<Map>();
			for (Document doc : result) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", doc.get("id"));
				map1.put("pw", doc.get("pw"));
				map1.put("name", doc.get("name"));
				map1.put("tel", doc.get("tel"));
				list.add(map);
			}
			
			System.out.println("=================");
			System.out.println(list);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
