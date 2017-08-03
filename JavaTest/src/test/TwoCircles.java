package test;

/**
 * Created by thofiq.khan on 8/4/2017
 */
public class TwoCircles {

    public static void main(String[] args) {

        //Space seperated values
        String[] inputArray = {"12 0 21 14 0 23", "0 45 8 0 94 9", "35 0 13 10 0 38",
                "0 26 8 0 9 25", "0 5 9 0 9 7","0 15 11 0 20 16", "26 0 10 39 0 23", "37 0 5 30 0 11", "41 0 0 28 0 13"};

        String[] circles = circles(inputArray);

        for(String circleDef : circles){
            System.out.println(circleDef);
        }

    }


    /**
     * Return the type of circles based on input co-ordinates
     * @param inputArray
     * @return
     */
    static String[] circles(String[] inputArray) {

        String[] resultArray = new String[inputArray.length];
        final String SPACE = " ";


        for(int i =0; i<inputArray.length; i++){
            String[] splitValue = inputArray[i].split(SPACE);

            //Initial values defaulted to zero
            int X1 = 0, Y1 = 0, radius1 = 0, X2 = 0, Y2 = 0, radius2 = 0;

            //Assumption expecting the input should be proper
            for(int j = 0;j<splitValue.length; j++){
                try {
                    X1 = Integer.parseInt(splitValue[0]);
                    Y1 = Integer.parseInt(splitValue[1]);
                    radius1 = Integer.parseInt(splitValue[2]);
                    X2 = Integer.parseInt(splitValue[3]);
                    Y2 = Integer.parseInt(splitValue[4]);
                    radius2 = Integer.parseInt(splitValue[5]);
                }catch (Exception e){
                    System.out.println(" Dear User Pls Provide Proper Input it should be in order of X1 Y1 Radius1 X2 Y2 Radius2");
                    return new String[0];
                }
            }

            //Constraints, as per problem when x1 x2 not zero then y1,y2 should be zero and vice versa
            if(X1 !=0 && X2 != 0){
                if(Y1 != 0 && Y2 != 0){
                    resultArray[i] = "Invalid input!!!!";
                }
            }else if(Y1 !=0 && Y2 != 0){
                if(X1 != 0 && X2 != 0){
                    resultArray[i] = "Invalid input!!!!";
                }
            }

            //Calculate the circle distance
            double distance = Math.sqrt(Math.pow((X1 - X2), 2) + Math.pow((Y1 - Y2), 2));

            double radius1MinusRadius2 = radius1 - radius2;
            double radius2MinusRadius1 = radius2 - radius1;

            if (distance == radius1MinusRadius2 || distance == radius2MinusRadius1) {
                resultArray[i] = "Touching";
            }else if(radius1 != radius2 && X1 == X2 && Y1 == Y2){
                resultArray[i] = "Concentric";
            } else if ((radius1MinusRadius2 <= distance) && (distance <= (radius1 + radius2))) {
                resultArray[i] = "Intersecting";
            } else if (distance > (radius1 + radius2)) {
                resultArray[i] = "Disjoint‐Outside";
            } else if ((radius2 >= radius1 && distance <= radius2MinusRadius1)
                    || (radius1 >= radius2 && distance <= radius1MinusRadius2)) {
                resultArray[i] = "Disjoint‐Inside";
            }

        }
        return  resultArray;
    }

}

