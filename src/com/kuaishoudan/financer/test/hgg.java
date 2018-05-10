package com.kuaishoudan.financer.test;

import java.util.ArrayList;
import java.util.List;

public class hgg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<Integer> sfs=new ArrayList<Integer>();

sfs.add(1);sfs.add(2);
List<Integer> sfs2=new ArrayList<Integer>();
sfs.add(1);sfs.add(3);
sfs.addAll(sfs2);
for(int i=0;i<sfs.size();i++){
	System.out.print(sfs.get(i));
}
	}

}
