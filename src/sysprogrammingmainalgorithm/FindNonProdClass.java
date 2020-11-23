package sysprogrammingmainalgorithm;

import JavaTeacherLib.MyLang;
import JavaTeacherLib.Node;

import java.util.*;
import java.util.stream.Collectors;

public class FindNonProdClass {
    MyLang lang;
    ArrayList<Integer> prodTerm;
    ArrayList<Integer> nonprodTerm;
    ArrayList<Node> nonprodRules;
    ArrayList<Node> prodRules;
    LinkedList<Node> langRules ;
    LinkedList<Node> langRules_withoutCurrent;
   int flag=1;

    FindNonProdClass(MyLang testLang) {
        this.lang = testLang;
        prodTerm = new ArrayList<>();
        nonprodTerm = new ArrayList<>();
        nonprodRules =new ArrayList<>();
        prodRules =new ArrayList<>();
        langRules=lang.getLanguarge();
        langRules_withoutCurrent=new LinkedList<>() ;
    }

    public void findNonProd() {
        int fS=langRules.size();
        find_Term(langRules);

        if(prodRules.size()==0){
            flag=4;
        }
        while(flag!=4){
            repeat();
            if(langRules.size()==fS){
                break;
            }
            else{
                fS=langRules.size();
            }
        }
      if(prodRules.size()!=0){
          for (int i = 0; i < langRules.size(); i++) {
         int[] rule1 = langRules.get(i).getRoole();
         ArrayList<Integer> rule = new ArrayList<>();
         for (int y = 0; y < rule1.length; y++) {
             rule.add(rule1[y]);
         }
         ArrayList<Integer> nonterm_of_right_side = new ArrayList<>();
         int left;

         if (langRules.size() > 0) {
             for (int l = 0; l < langRules.size(); l++) {
                 rule1 = langRules.get(l).getRoole();
                 rule.clear();
                 for (int y = 0; y < rule1.length; y++) {
                     rule.add(rule1[y]);
                 }
                 left = rule.get(0);
                 nonterm_of_right_side = find_nonTerm(rule);
                 if (nonterm_of_right_side.contains(left)) {
                     for (int r = 0; r < prodRules.size(); r++) {
                         Node rule2 = prodRules.get(r);
                         if (rule2.getRoole()[0] == left) {
                             flag = 5;
                             break;
                         }
                     }
                     if (flag != 5) {
                         nonprodTerm.add(left);
                         nonprodRules.add(langRules.get(l));
                         flag = 2;
                     }

                 }
             }
         }
         if(nonprodRules.size()!=0){
                  for(int q=0;q<nonprodRules.size();q++)
                      langRules.remove(nonprodRules.get(q));
                  langRules_withoutCurrent=langRules;
          }
              langRules_withoutCurrent = langRules;
         while(flag!=7){
             replaceNontermWithTerm();
         }
         for(int s=0;s<langRules_withoutCurrent.size();s++){
             int[] rule3 = langRules_withoutCurrent.get(s).getRoole();
             ArrayList<Integer> rule4 = new ArrayList<>();
             for (int y = 0; y < rule3.length; y++) {
                 rule4.add(rule3[y]);
             }
             if(find_nonTerm(rule4).size()>0){
                 nonprodTerm.add(rule4.get(0));
                 nonprodRules.add(langRules_withoutCurrent.get(s));
             }
         }
      }
    }
   }

   public  void repeat(){
       for (int i = 0; i < langRules.size(); i++) {
           int[] rule1 = langRules.get(i).getRoole();
           ArrayList<Integer> rule = new ArrayList<>();
           for(int y=0;y<rule1.length;y++) {
               rule.add(rule1[y]);
           }
           ArrayList<Integer> nonterm_of_right_side = find_nonTerm(rule);
           if(nonterm_of_right_side.size()>0) {
               int left = rule.get(0);
               for(int u=0;u<nonterm_of_right_side.size();u++){
                   for (int j = 0; j < langRules.size(); j++) {
                       if (j != i) {
                           if(nonterm_of_right_side.get(u).equals(langRules.get(j).getRoole()[0])){
                               flag = 0;
                           }
                       }
                   }
                   if (flag == 1) {
                       nonprodTerm.add(left);
                       nonprodTerm.add(nonterm_of_right_side.get(u));
                       nonprodRules.add(langRules.get(i));
                       break;
                   }

               }
               flag=1;
           }
       }
       if(nonprodRules.size()!=0){
           for(int q=0;q<nonprodRules.size();q++)
              langRules.remove(nonprodRules.get(q));
           langRules_withoutCurrent=langRules;
       }


   }

   public  void replaceNontermWithTerm() {
       find_Term(langRules_withoutCurrent);
       ArrayList<Node> prodRules1 = new ArrayList<>();
       prodRules1 = prodRules;
       for(int q=0;q<prodRules1.size();q++) {
            langRules_withoutCurrent.remove(prodRules1.get(q));
       }

       LinkedList<Node> langRules_withoutCurrent1=new LinkedList<>();
       for (int i = 0; i < langRules_withoutCurrent.size(); i++) {
           for (int j = 0; j < prodRules1.size(); j++) {
               {
                   int[] rule1 = langRules_withoutCurrent.get(i).getRoole();
                   ArrayList<Integer> rule = new ArrayList<>();
                   ArrayList<Integer> rule_final = new ArrayList<>();
                   for (int y = 0; y < rule1.length; y++) {
                       rule.add(rule1[y]);
                   }
                   for (int y = 0; y < rule.size(); y++) {
                       if(rule.get(y)<0 && y!=0)
                       {
                           if (rule.get(y)==prodRules1.get(j).getRoole()[0])
                           {
                               for(int w=1;w<prodRules1.get(j).getRoole().length;w++){
                                   rule_final.add(prodRules1.get(j).getRoole()[w]);
                               }

                           }
                           else{
                               rule_final.add(rule.get(y));
                           }
                       }
                       else{
                           rule_final.add(rule.get(y));
                       }
                   }
                   int[] r = rule_final.stream().mapToInt(Integer::intValue).toArray();
                   Node roole=new Node(r,r.length);
                   langRules_withoutCurrent1.add(roole);
               }
           }

       }
       for(int i=0;i<langRules_withoutCurrent.size();i++) {
           for(int j=0; j<langRules_withoutCurrent.get(i).getRoole().length;j++) {
               if (langRules_withoutCurrent.get(i).getRoole()[j] == langRules_withoutCurrent1.get(i).getRoole()[j]) {
                   flag = 7;
               } else {
                   flag = 8;
                   break;
               }
           }
       }
       langRules_withoutCurrent=langRules_withoutCurrent1;
   }
       public void print () {
           prodTerm = (ArrayList<Integer>) prodTerm.stream()
                   .distinct()
                   .collect(Collectors.toList());
           nonprodTerm = (ArrayList<Integer>) nonprodTerm.stream()
                   .distinct()
                   .collect(Collectors.toList());
           nonprodRules = (ArrayList<Node>) nonprodRules.stream()
                   .distinct()
                   .collect(Collectors.toList());
           Set<Node> set = new HashSet<>(nonprodRules);
           nonprodRules.clear();
           nonprodRules.addAll(set);
           if (nonprodTerm.size() > 0) {
               System.out.println("Непродуктивні правила : ");
               for (Node n : nonprodRules
               ) {
                   lang.prpintRoole(n);
               }

           } else if (flag == 4) {
               System.out.println("Непродуктивні правила : ");
               for (Node n : langRules
               ) {
                   lang.prpintRoole(n);
               }
           } else {
               System.out.println("Непродуктивні правила відсутні");
           }
       }

       public ArrayList<Integer> find_nonTerm (ArrayList < Integer > rule) {
           ArrayList<Integer> nonterm = new ArrayList<>();
           for (int k = 1; k < rule.size(); k++) {
               if (rule.get(k) < 0) {
                   nonterm.add(rule.get(k));
               }

           }
           nonterm = (ArrayList<Integer>) nonterm.stream()
                   .distinct()
                   .collect(Collectors.toList());
           return nonterm;
       }

       public void find_Term (LinkedList<Node> langRules1) {
           for (int k = 0; k < langRules1.size(); k++) {
               int[] rule = langRules1.get(k).getRoole();
               int left = rule[0];
               for (int p = 1; p < rule.length; p++) {
                   if (rule[p] > 0 && p != rule.length - 1) {
                       continue;
                   } else if (rule[p] > 0 && p == rule.length - 1) {
                       prodTerm.add(left);
                       prodRules.add(langRules1.get(k));
                   } else {
                       break;
                   }
               }
           }
           prodTerm = (ArrayList<Integer>) prodTerm.stream()
                   .distinct()
                   .collect(Collectors.toList());
           prodRules=(ArrayList<Node>) prodRules.stream()
                   .distinct()
                   .collect(Collectors.toList());
       }


}
