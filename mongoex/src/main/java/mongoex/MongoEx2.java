package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx2 {

	public static void main(String[] args) {
		// Unchecked Exception
		// 0으로 나누기, 배열/리스트의 인덱스 오류
		
		// Checked Exception --> 반드시 try 처리
		
		
		try {
			MongoClient client = new MongoClient(new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));

			MongoDatabase database = client.getDatabase("shop2");

			MongoCollection<Document> collection = database.getCollection("member");

			Document doc = new Document();
			doc.append("id", "apple");
			doc.append("pw", "1234");
			doc.append("name", "사과");
			doc.append("tel", "011");

			collection.insertOne(doc);
			System.out.println("insertOne 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
