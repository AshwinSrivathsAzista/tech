package test1;

interface Vok {
    public Boolean trans(String msg);
    public String res();
}


class Vok1 implements Vok {
    @Override
    public Boolean trans(String msg){
        System.out.println(msg);
        return true;
    }

    @Override
    public String res(){
        return "Response from Vok1";
    }
}




public class Test {
    public static void main(String[] args) {
        Vok vok = new Vok1();
        vok.trans("Hello from Vok!");
        String result = vok.res();
        System.out.println("Result: " + result);
    }
}