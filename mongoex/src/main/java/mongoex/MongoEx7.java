package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class MongoEx7 {

	public static void main(String[] args) {
		
		try(MongoClient client = 
				new MongoClient(new MongoClientURI
						("mongodb://user:1234@localhost:27017/shop"));) {
			
			MongoDatabase database = client.getDatabase("shop");
			
			MongoCollection<Document> collection = database.getCollection("memo");
			
			Document doc = new Document();
			doc.append("age", "500");
			doc.append("name", "song");
			doc.append("office", "busan");
			doc.append("phone", "011");
				
			
			//collection.insertOne(doc);
			
			//System.out.println("insertOne 성공"); 
			
			List<Document> list = new ArrayList<>();
			for (int i = 1; i <= 4; i++) {
				Document docs = new Document();
				docs.append("age", "50" + i);
				docs.append("name", "song" + i);
				docs.append("office", "busan");
				docs.append("phone", "011");
				
				list.add(docs);
			}
			
			//collection.insertMany(list);
			
			//System.out.println("insertMany 성공");
			
			/*Document filter = new Document("age", "500");
			collection.deleteOne(filter);
			System.out.println("삭제 성공");*/
			
			// 업데이트
			/*
			 * Bson updateFilter = new Document("age", "501"); Bson update =
			 * Updates.set("name", "songsong"); collection.updateOne(updateFilter, update);
			 * System.out.println("updateOne 성공");
			 */
			
			
			Bson updateFilter2 = new Document("office", "busan");
			Bson update2 = Updates.combine(
						Updates.set("phone", "9999"),
						Updates.set("name", "songsong2"),
						Updates.set("age", "555")
					);
			
			collection.updateMany(updateFilter2, update2);
			System.out.println("updateMany 성공");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
