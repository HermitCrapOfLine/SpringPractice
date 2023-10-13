package mongoex;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx3 {
	public static void main(String[] args) {
		
		try {
			// 1. 몽고DB에 연결
			MongoClient client = new MongoClient(new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));
			
			// 2. shop3 db에 연결
			MongoDatabase database = client.getDatabase("shop2");
			
			// 3. member collection에 연결
			MongoCollection<Document> collection
				= database.getCollection("member");
			
			// 4. crud(insertOne(document)
			Scanner sc = new Scanner(System.in);
			
			System.out.print("id입력>>");
			String id = sc.next();
			System.out.print("pw입력>>");
			String pw = sc.next();
			System.out.print("name입력>>");
			String name = sc.next();
			System.out.print("tel입력>>");
			String tel = sc.next();
			sc.close();
			
			Document doc = new Document();
			doc.append("id", id);
			doc.append("pw", pw);
			doc.append("name", name);
			doc.append("tel", tel);
			
			System.out.println(doc);
			collection.insertOne(doc);
			System.out.println(doc);
			
			System.out.print("insertOne 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
