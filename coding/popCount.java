class Scratch {
    public static void main(String[] args) {

//        int result = popCount(-1);
        int result = popCount(5);
        System.out.println(result);
    }



    public static int popCount(int a){

        int validResult = Integer.bitCount(a);

        int mask = 0x55555555;
        a = a & mask + ((a >>> 1) & mask);
//        System.out.println(Integer.toBinaryString(a));

        int mask2 = 0x33333333;
        a = (a & mask2) + ((a >>> 2) & mask2);
//        System.out.println(Integer.toBinaryString(a));

        int mask3 = 0x0F0F0F0F;
        a = (a & mask3) + ((a >>> 4) & mask3);
//        System.out.println(Integer.toBinaryString(a));

        int mask4 = 0x00FF00FF;
        a = (a & mask4) + ((a >>> 8) & mask4);
//        System.out.println(Integer.toBinaryString(a));

        int mask5 = 0x0000FFFF;
        a = (a & mask5) + ((a >>> 16) & mask5);
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(a);

        return a;
    }


}