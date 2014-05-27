package org.coco.test.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class SerializableTestCase extends TestCase {

	// public void testWriteObject() {
	// boolean successFlag = true;
	// ObjectOutputStream oos = null;
	// try {
	// String filePathAndName =
	// "E:/iCloud/workspace/COCO/src/org/coco/test/serializable/Person.ser";
	// oos = new ObjectOutputStream(new FileOutputStream(new File(
	// filePathAndName)));
	// oos.writeObject(new Person());
	// successFlag = true;
	// } catch (FileNotFoundException e) {
	// successFlag = false;
	// e.printStackTrace();
	// } catch (IOException e) {
	// successFlag = false;
	// e.printStackTrace();
	// } finally {
	// if (oos != null) {
	// try {
	// oos.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// assertTrue(successFlag);
	// }

	public void testReadObject() {
		boolean successFlag = true;
		ObjectInputStream ois = null;
		try {
			String filePathAndName = "E:/iCloud/workspace/COCO/src/org/coco/test/serializable/Person.ser";
			ois = new ObjectInputStream(new FileInputStream(new File(
					filePathAndName)));
			Person person = (Person) ois.readObject();
			System.out.println(person);
			successFlag = true;
		} catch (FileNotFoundException e) {
			successFlag = false;
			e.printStackTrace();
		} catch (IOException e) {
			successFlag = false;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			successFlag = false;
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		assertTrue(successFlag);
	}

	public static void main(String[] args) {

	}

}
