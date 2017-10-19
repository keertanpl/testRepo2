package com.landg.lqa.objectrepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import com.landg.lqa.utility.*;

public class CreateObjectRepository {

	public static void Create() throws Exception {
		// TODO Auto-generated method stub

		HashMap<Integer, String> ObjectTable_0 = new HashMap<Integer, String>();
		HashMap<Integer, String> ObjectTable_1 = new HashMap<Integer, String>();
		int i = 1;
		int key1, key2;
		String Value, Question, Tag, Locator;
		Init.setObjectRepository();
		do {

			Question = ExcelUtils.getCellData("",i, 0);
			Value = ExcelUtils.getCellData("",i, 1);
			Tag = ExcelUtils.getCellData("",i, 3);
			Locator = ExcelUtils.getCellData("",i, 2);

			if (Tag.compareTo("Button") == 0) {

				key1 = (Question.hashCode() + Value.hashCode());
				key2 = (Question.hashCode() + Value.hashCode() + 10);
				ObjectTable_0.put(key1, Locator);
				ObjectTable_0.put(key2, Tag);

			} else {
				key1 = (Question.hashCode());
				key2 = (Question.hashCode() + 10);

				ObjectTable_0.put(key1, Locator);
				ObjectTable_0.put(key2, Tag);

			}

			i++;

		} while (!(ExcelUtils.getCellData("",i, 0).equalsIgnoreCase("")));
		i = 1;
		System.out.println("Object Repository Created/Updated Successfully");

		FileOutputStream fileOut_0 = new FileOutputStream(
				System.getProperty("user.dir") + "\\Object Repository"
						+ "\\Object_0.ser");
		ObjectOutputStream out_0 = new ObjectOutputStream(fileOut_0);
		out_0.writeObject(ObjectTable_0);

		do {

			Question = ExcelUtils.getCellData("",i, 0);

			Tag = ExcelUtils.getCellData("",i, 3);
			Locator = ExcelUtils.getCellData("",i, 2);
			if (Tag.compareTo("Button") != 0) {
				key1 = (Question.hashCode());
				key2 = (Question.hashCode() + 10);

			} else {
				Value = ExcelUtils.getCellData("",i, 1);
				key1 = (Question.hashCode() + Value.hashCode());
				key2 = (Question.hashCode() + Value.hashCode() + 10);
			}

			i++;

		} while (i < 56);
	}

}
