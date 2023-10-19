package mongoex;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class MongoInsertEx {

	public static void main(String[] args) {
				
		try (MongoClient client = new MongoClient(new MongoClientURI
				("mongodb://winner:1234@localhost:27017/shop2"));) {
				// 인증 방식으로 DB에 먼저 접속한다.
			
			// 1. shop2 db에 연결
			MongoDatabase database = client.getDatabase("shop2");

			// 2.member collection에 연결
			MongoCollection<Document> collection = database.getCollection("member");
			
			// 프로퍼티 name에서 Mccoy이란 사람을 찾는다. 
			Bson filter = new Document("name", "Mccoy");
			
			// filter의 조건에 맞은 사람을 찾으면 하나를 삭제한다.
			collection.deleteOne(filter);
			
			System.out.println("DeleteOne 성공");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
