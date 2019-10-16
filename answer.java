package rjgcsecondwork;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;
public class Answer {
		public static int[] answer(String str){
			String temp1 =" ";
			int result[]= {1,1,1};
			int num[]= {0,0,0,0,0};
			char operator []= {' ',' ',' ',' '};
			int i=0;
			int j=0;
		     String temp=null;
		    	 int num1[]= {1,1,1};
	             int num2[]= {1,1,1};
	             int num3[]= {1,1,1};
		         int parplace=-1;
		         char sss[] = str.toCharArray();
		         //存储括号里的数据
		         for(i=0;i<str.length();i++)
		         {
		        	 if(sss[i]=='(') {
		        		 j=1;
		        	 }
		        	 if(j==1 ) {
		        		 temp=temp+sss[i];
		        	 }
		        	 if(sss[i]==')') {
		        		 j=0;
		        	 }
		         }

		         String getSignInfo = temp;
		         boolean tap=true;
		        
		         //提取括号里的数字和运算符
		         char s[] = getSignInfo.toCharArray();
		         int parantnum []= {0,0};
		         Pattern p2 =Pattern.compile("^[1-9]\\d*|0$");
		         Matcher m2 = p2.matcher(getSignInfo);
		         i=0;
		         while (m2.find()) {//通过while循环获得一道题目中的所有数字
		        	 temp1=m2.group();
		        	 parantnum[i]=Integer.parseInt(temp1);
		        	 i++;
		        }
		         
		         for(i=0;i<getSignInfo.length();i++) {
		        	 j=0;
		             num1[0]=parantnum[0];
		             num1[1]=parantnum[1];
		             num1[2]=parantnum[2];
		             num2[0]=parantnum[3];
		             num2[1]=parantnum[4];
		             num2[2]=parantnum[5];
		        	 if(s[i]=='+') {
		        		 num3=Addition.Add(num1,num2);
		        	 }
		        	 if(s[i]=='-') {
		        		 num3=Substraction.Substract(num1,num2);
		        	 }
		        	 if(s[i]=='x') {
		        		 num3=Multiplication.Multiply(num1,num2);
		        	 }
		        	 if(s[i]=='÷') {
		        		 num3=Division.Divide(num1,num2);
		        	 }
		        }
		             //求出括号里面的内容后，把结果存进num数组中，看作一个操作数
		             if(-1 < parplace && parplace < 6) {
		            	 num[0]=num3[0];
		            	 num[1]=num3[1];
		            	 num[2]=num3[2];
		             }
		             if(6<=parplace && parplace<=8) {
		            	 num[3]=num3[0];
		            	 num[4]=num3[1];
		            	 num[5]=num3[2];
		             }
		             if(9<=parplace && parplace<=17) {
		            	 num[6]=num3[0];
		            	 num[7]=num3[1];
		            	 num[8]=num3[2];
		             }
		             if(18<=parplace) {
		            	 num[9]=num3[0];
		            	 num[10]=num3[1];
		            	 num[11]=num3[2];
		             }
		         //删除题目中的括号包含数据
		             for(i=0;i<str.length();i++) {
			        	 if(sss[i]=='(') {
			        		 parplace=i;
			        		 tap=false;
			        	 }
			        	 if(tap==false) {
			        		 str.replace("[0-9]","");
			        	 }
			        	 if(sss[i]==')') {
			        		 tap=true;
			        	 }
			         }
		             
		         temp=str;
		         
		         
		         Pattern p1 =Pattern.compile("^[0-9]*$");//提取数字
		         Matcher m1 = p1.matcher(temp);
		       //通过while循环获得一道题目中的所有数字，此时已经没有括号
		         i=0;
		         while (m1.find()) { 
		        	 temp1=m1.group();
		        	 while(num[i]!=0) {
		        		 i++;
		        	 }
		        	 num[i]=Integer.parseInt(temp1);
		          }
		        //通过对字符串的遍历，获取各运算符，=   — x  /
		         char ss[] = str.toCharArray();
		         j=0;
		         for(i=0;i<str.length();i++) {
		        	 if(ss[i]=='+') {
		        		 operator[j]=ss[i];
		        		 j++;
		        	 }
		        	 if(ss[i]=='-') {
		        		 operator[j]=ss[i];
		        		 j++;
		        	 }
		        	 if(ss[i]=='x') {
		        		 operator[j]=ss[i];
		        		 j++;
		        	 }
		        	 if(ss[i]=='÷') {
		        		 operator[j]=ss[i];
		        		 j++;
		        	 }
		         }
		         for(i=0;i<3;i++) {
		        	 if(operator[i]=='x') {
		        		 num1[0]=num[3*i];
			             num1[1]=num[3*i+1];
			             num1[2]=num[3*i+2];
			             num2[0]=num[3*i+3];
			             num2[1]=num[3*i+4];
			             num2[2]=num[3*i+5];
			             int numtemp[ ]= {0};
		        		 num3=Multiplication.Multiply(num1,num2);
		        		 j=0;
		        		 num[3*i]=num3[0];
			             num[3*i+1]=num3[1];
			             num[3*i+2]=num3[2];
			             num[3*i+3]=0;
			             num[3*i+4]=0;
			             num[3*i+5]=0;
			             operator[i]=' ';
		        		 for(i=0;i<12;i++) {
		        			 if(num[i]!=0)
		        				 numtemp[j]=num[i];
		        			     j++;
		        	   }
		        		 for(i=0;i<12;i++) {
		        			 num[i]=numtemp[i];
		        		 }
		        	 if(operator[i]=='÷') {
		        		 num1[0]=num[3*i];
			             num1[1]=num[3*i+1];
			             num1[2]=num[3*i+2];
			             num2[0]=num[3*i+3];
			             num2[1]=num[3*i+4];
			             num2[2]=num[3*i+5];
		        		 num3=Division.Divide(num1,num2);
		        		 j=0;
		        		 num[3*i]=num3[0];
			             num[3*i+1]=num3[1];
			             num[3*i+2]=num3[2];
			             num[3*i+3]=0;
			             num[3*i+4]=0;
			             num[3*i+5]=0;
			             operator[i]=' ';
		        		 for(i=0;i<12;i++) {
		        			 if(num[i]!=0)
		        				 numtemp[j]=num[i];
		        			     j++;
		        	   }
		        		 for(i=0;i<12;i++) {
		        			 num[i]=numtemp[i];
		        		 }
		        	 }
		         }
		         
		     }
		         for(i=0;i<3;i++) {
		        	 if(operator[i]=='+') {
		        		 num1[0]=num[3*i];
			             num1[1]=num[3*i+1];
			             num1[2]=num[3*i+2];
			             num2[0]=num[3*i+3];
			             num2[1]=num[3*i+4];
			             num2[2]=num[3*i+5];
			             int numtemp[ ]= {0};
		        		 num3=Addition.Add(num1,num2);
		        		 j=0;
		        		 num[3*i]=num3[0];
			             num[3*i+1]=num3[1];
			             num[3*i+2]=num3[2];
			             num[3*i+3]=0;
			             num[3*i+4]=0;
			             num[3*i+5]=0;
			             operator[i]=' ';
		        		 for(i=0;i<12;i++) {
		        			 if(num[i]!=0)
		        				 numtemp[j]=num[i];
		        			     j++;
		        	   }
		        		 for(i=0;i<12;i++) {
		        			 num[i]=numtemp[i];
		        		 }
		        	 if(operator[i]=='-') {
		        		 num1[0]=num[3*i];
			             num1[1]=num[3*i+1];
			             num1[2]=num[3*i+2];
			             num2[0]=num[3*i+3];
			             num2[1]=num[3*i+4];
			             num2[2]=num[3*i+5];
		        		 num3=Substraction.Substract(num1,num2);
		        		 j=0;
		        		 num[3*i]=num3[0];
			             num[3*i+1]=num3[1];
			             num[3*i+2]=num3[2];
			             num[3*i+3]=0;
			             num[3*i+4]=0;
			             num[3*i+5]=0;
			             operator[i]=' ';
		        		 for(i=0;i<12;i++) {
		        			 if(num[i]!=0)
		        				 numtemp[j]=num[i];
		        			     j++;
		        	   }
		        		 for(i=0;i<12;i++) {
		        			 num[i]=numtemp[i];
		        		 }
		        	 }
		        }
		    }
		      return num;
		}
	
		 public static void main(String[] args) {
			  String str="3'2/3 + 4'4/5 -2'1/2 =";
			  int num[]= {1,1,1};
			  num = answer(str);
			  System.out.println(num[0] + "' " + num[1] + "/" + num[2]);
			 }
}
