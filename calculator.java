import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class demo{
    private static boolean isElementPresent(char[] arr, char key) {
        for(int i = 0; i<arr.length; i++){
            if(arr[i] == key){
                return true;
            }
        }
        return false; 
    }
    private static List<Object> numberMaker(char[] rawArray){
        List<Object> eqList = new ArrayList<Object>();
        String numTemp = "";
        char[]numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-'};
        for(int i = 0; i<rawArray.length; i++){
            if(isElementPresent(numbers, rawArray[i])){
                if(rawArray[i]=='-'){
                    eqList.add(Integer.parseInt(numTemp));
                    numTemp = "";
                    numTemp += rawArray[i];
                }
                else{
                    numTemp += rawArray[i];
                }
                
                if(i==rawArray.length-1){
                    eqList.add(Integer.parseInt(numTemp));
                }
            }
            else{
                if(numTemp != ""){
                    eqList.add(Integer.parseInt(numTemp));
                    numTemp = "";
                }
                eqList.add(rawArray[i]);
            }
        
        }
        return eqList;
    }

    public static void main(String[]args){
        int answer;
        int multAns;
        int divAns;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter an equation:");
        String equationString = scnr.nextLine();
        char[] equationArray = equationString.toCharArray();
        List<Object> equationList = numberMaker(equationArray);
        List<Object> multDivHandled = new ArrayList<Object>();
        boolean lastOpperatorMD = false;
        System.out.println(equationList);
        //first pass
        for(int i = 0; i<equationList.size(); i++){
            if(lastOpperatorMD!=true && equationList.get(i).toString().equals("X")){
                    multAns = (int)equationList.get(i-1) * (int)equationList.get(i+1);
                    multDivHandled.add(multAns);
                    lastOpperatorMD = true;
                }
            
            else if(lastOpperatorMD!=true && equationList.get(i).toString().equals("/")){
                    divAns = (int)equationList.get(i-1) / (int)equationList.get(i+1);
                    multDivHandled.add(divAns);
                    lastOpperatorMD = true;
                }
            

            else if(lastOpperatorMD!=true && equationList.get(i).toString().equals("+")){
                multDivHandled.add(equationList.get(i-1));
                multDivHandled.add(equationList.get(i));
                lastOpperatorMD = false;
            }
            
            else if(lastOpperatorMD && equationList.get(i).toString().equals("+")){
                multDivHandled.add(equationList.get(i));
                multDivHandled.add(equationList.get(i+1));
                lastOpperatorMD = false;
            }
            
            else if(lastOpperatorMD && equationList.get(i).toString().equals("X")){
                    multAns = (int)multDivHandled.get(multDivHandled.size()-1) * (int)equationList.get(i+1);
                    multDivHandled.set(multDivHandled.size()-1, multAns);
                    lastOpperatorMD = true;
                }
            
            else if(lastOpperatorMD && equationList.get(i).toString().equals("/")){
                    divAns = (int)multDivHandled.get(multDivHandled.size()-1) / (int)equationList.get(i+1);
                    multDivHandled.set(multDivHandled.size()-1, divAns);
                    lastOpperatorMD = true;
                }
        }
        System.out.println(multDivHandled);
        }

    }
