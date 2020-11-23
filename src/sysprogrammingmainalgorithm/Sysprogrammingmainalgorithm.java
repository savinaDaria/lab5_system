/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sysprogrammingmainalgorithm;
//package JavaTeacherLib;
/** 
 *
 * @author VVN
 */
import java.lang.*; 
import java.util.*;
import java.io.*;
import java.nio.charset.Charset;


public class Sysprogrammingmainalgorithm {

 public static void main(String[] args) {
      String readline;
      boolean result;
      String fileName;
     JavaTeacherLib.MyLang testLang=null;
      int codeAction, llk=1, textLen;
     String[] menu = new String[]{"*1.  ��������� ��������� � �����  ",
              " 2.  ����������� ������. ���� ���� �������",
              " 3.  ����������� ���������",
              "*4.  ���������� ������ �������� �� ����������",
              "*5.  ����� �������������� ����������",
              "*6.  ����� ���������� ����������",
              "*7.  �������� ������ ������-����������",
              " 8.  ���� ������ ������-����������",
              " 9.  ����� �������������� ����������",
              " 10. ����� ����� �������������� ������ �������� �������",
              " 11. ����� ���������������� ����������",
              " 12. ����� ����� ���������������� ������ �������� �������",
              "*13. ���������� ������� FirstK(A), A-���������",
              " 14. ������� �� ������� ������� FirstK(A), A-���������",
              "*15. ���������� ������� FollowK(A), A-���������",
              " 16. ������� �� ������� ������� FollowK(A), A-���������",
              "*17. ���������� ������� FirstK(w) + FollowK(A) ��� ������� �->w",
              " 18. ������� �� ������� FirstK(w) + FollowK(A) ��� ��� ������ �->w",
              " 19. ������� �� ������� FirstK(w) + FollowK(A) ��� �������� ������ �->w",
              "*20. �������� ������ LL(1)-����������",
              " 21. �������� ������� LL(1)-������������� ����������",
              " 22. ������������ ���������. ���� ���� �������",
              "*23. ���������� ������� LocalK(A), A-���������",
              " 24. ������� �� ������� ������� LocalK(A), A-���������",
              "*25. �������� LL(k)-����������, k>1",
              " 26. ����� � �������"
      };
      Scanner scan = new Scanner(System.in);
     do  {
       codeAction=0; 
       String upr;
      for (String ss: menu) System.out.println(ss); // ������� ����
      System.out.println("������ ��� 䳿 ��� end:");
     do {  // ���� �������� �����
       try {
          readline = scan.nextLine();
          upr = readline;
        if (upr.trim().equals("end") ) return;
        codeAction=new Integer (upr.trim());
          }
       catch(Exception ee) 
            { System.out.println ("������� ��� 䳿, ��������: ");
             continue;
            }
       if (codeAction >=1  &&  codeAction<=menu.length ) {
           if (menu [codeAction-1].substring(0, 1).equals("+"))  {
            System.out.println("������� ���� " +codeAction+" �������� �������� ���������"); 
            continue ;
            }
           int itmp;
           for (itmp=0; itmp < codeAction-1; itmp++)
               if (menu[itmp].substring(0, 1).equals("*")) break; 
           if (itmp !=codeAction-1) {
               System.out.println ("��������� �������� �������� ����, �� �������� * : ");
               continue ; 
              }  
           break;
          }
          else {
            System.out.println ("������� ��� 䳿, ��������: ");
            continue ;
           }
     }  while (true);
     // �������� �� ��������� ��� ��������� ��
     result=false;
       switch (codeAction) {
            case 1: //1. ��������� ��������� � �����",
                System.out.println ("������ ��'� ����� ���������: "); 
                 try {
                    readline = scan.nextLine(); 
                    fileName = readline;
                    System.out.println ("��'� ����� ���������: "+ fileName);
                    fileName = fileName.trim();
                     }
                   catch(Exception ee) 
                        { System.out.println ("�������� �������: "+ee.toString());
                          return;
                        }
                System.out.println ("������ �������� ��������� k : ");
                try {
                    readline = scan.nextLine();
                    String llkText = readline;
                    llkText = llkText.trim();
                    llk=Integer.parseInt(llkText);
                     }
                   catch(Exception ee) 
                        { System.out.println ("�������� �������: "+ee.toString());
                          return;
                        }
                testLang = new JavaTeacherLib.MyLang (fileName,llk);
                if (!testLang.isCreate()) break;  //�� �������� ��'��� 
                System.out.println ("��������� ��������� ������");
                result=true; 
                for (int jj=0;  jj<menu.length; jj++) {
                   if (menu [jj].substring(0, 1).equals(" ")) continue;
                   menu [jj]=menu [jj].replace(menu [jj].charAt(0), '*') ;  
                  } 
                   break;
            case 2: //2. ����������� ������ ��������
                //  ����� ��� �������� ��������
                FindNonProdClass rules=new FindNonProdClass(testLang);
                rules.findNonProd();
                rules.print();
                break;
            case 3:  // ����������� ���������
                 testLang.printGramma();
                 break;
            case 4:  // ����������� ������ �������� �� ����������
                 testLang.printTerminals(); 
                 testLang.printNonterminals(); 
                 result=true;
                break;
            case 5: // ������� ������������ �������
                 result=testLang.createNonProdRools(); 
                 break;
            case 6: // �������� ����������
                result=testLang.createNonDosNeterminals(); 
                 break;
             case 7:  //�������� ������ ������-����������
                  int [] epsilon=testLang.createEpsilonNonterminals ();
                  testLang.setEpsilonNonterminals (epsilon);
                  result=true;
                 break;
             case 8: //���� ������ ������-����������
                  testLang.printEpsilonNonterminals(); 
                 break;
             case 9:    //����� �������������� ����������"
                 testLang.leftRecursNonnerminal();
                  break;
               case 10:  //����� ����� �������������� ������ �������� �������"
                  // testLang.leftRecusionTrace();
                   break;
               case 11:  //����� ���������������� ����������"
                    testLang.rightRecursNonnerminal();
                   break;
               case 12:  //����� ����� ���������������� ������ �������� �������"
                  //testLang.rigthRecusionTrace();
                   break;
               case 13:  //���������� ������� FirstK
                   JavaTeacherLib.LlkContext [] firstContext = testLang.firstK();
                   testLang.setFirstK(firstContext);
                   result=true;
                   break;
               case 14:  //���� ������� FirstK
                   testLang.printFirstkContext ( );
                   break;
               case 15:  //���������� ������� FollowK
                   JavaTeacherLib.LlkContext [] followContext = testLang.followK();
                   testLang.setFollowK(followContext);
                   result=true;
                   break;
             case 16:  //���� ������� FollowK
                   testLang.printFollowkContext ( );
                   break;
             case 17:  //���������� ������� FirstK(w) + FollowK(A) ��� ������� �->w
                   testLang.firstFollowK ( );
                   result=true;
                   break;
              case 18:  //���� ������� FirstK(w) + FollowK(A) ��� ������� �->w
                   testLang.printFirstFollowK( );
                   break;
              case 19:  //���� ������� FirstK(w) + FollowK(A) ��� �������� ������ �->w
                   testLang.printFirstFollowForRoole(); 
                   break;
             case 20:  //�������� ������ LL(k)-����������",
                 result=testLang. strongLlkCondition () ; 
                    break;    
            case 21:  //�������� ������� LL(1)-������������� ����������
             //     int [][] uprTable=testLang.createUprTable ();
             //       testLang.setUprTable(uprTable);
                   break;
             case 22: // PASCAL
                break;
                
             case 23: // 23. ���������� ������� LocalK(A), A-���������
                LinkedList <JavaTeacherLib.LlkContext> [] Localk=testLang.createLocalK();
                testLang.setLocalkContext(Localk);
                result=true;
                break; 
             case 24: // 24. ������� �� ������� ������� LocalK(A), A-���������
                   testLang.printLocalk();
                   break;
             case 25: // 25. �������� LL(k)-����������, k>1
                result= testLang.llkCondition();
                 break;
             case 26: // rtrtrtr   
                 return;
             case 27:
                 break;
             }  // ����� switch
       // ������� ������� �������
       if (result) // ������� �������� ������
           if (menu [codeAction-1].substring(0, 1).equals("*")) 
                 menu [codeAction-1]=menu [codeAction-1].replace('*', '+') ; 
   } while (true);  //���������� ����  �������
             
 }  // ����� main
 
 }

