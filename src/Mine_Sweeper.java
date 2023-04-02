
import java.util.Scanner;
import java.util.Random;

public class Mine_Sweeper {
    //Değişkenleri burada tanımladım.
    int en = 0,boy = 0;
    boolean isWin=false,isLose=false;
    String[][] oyunalani;
    String[][] mayinlar;



    //Burada hata kontrolüde yaparak kullanıcıdan alan genisliği bilgisini alacağım.
    public void alan_boyutu_al(){
        Scanner input = new Scanner(System.in);
        //Burada en değerini hata kontrolü yaparak aldım.
        boolean isEnValid = false,isBoyValid = false;
        do{
            System.out.print("Alanın en degeri(3-30):");
            try{
                this.en = input.nextInt();
                if (this.en>=3 & this.en<=30){
                    isEnValid = true;
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Lütfen 3-30 arasında bir tamsayı giriniz....");
                input.next();
            }
        }while (!isEnValid);

        //burada boy değerini hata kontrolü yaparak aldım.
        do{
            System.out.print("Alanın boy degeri(3-24):");
            try{
                this.boy = input.nextInt();
                if (this.boy>=3 & this.boy <=24){
                    isBoyValid = true;
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Lütfen 3-24 arasında bir tamsayı giriniz....");
                input.next();
            }
        }while (!isBoyValid);
    }

    public void array_olustur_mayinlari_yerlestir(){
        Random random = new Random();
        int mayin_say = Math.floorDiv(en*boy,4), temp_mayin_say = 0;
        this.oyunalani = new String[boy][en];
        this.mayinlar = new String[boy][en];

        //Burada listelere default değerleri atadım.
        for (int satir_no = 0; satir_no < boy; satir_no++) {
            for (int sutun_no = 0; sutun_no < en; sutun_no++) {
                this.oyunalani[satir_no][sutun_no] = "-";
                this.mayinlar[satir_no][sutun_no] = " ";
            }
        }
        //Burada mayınları yerleştirdim.
        while (temp_mayin_say < mayin_say) {
            int random_satir = random.nextInt(boy);
            int random_sutun = random.nextInt(en);

            if (!this.mayinlar[random_satir][random_sutun].equals("*")) {
                this.mayinlar[random_satir][random_sutun] = "*";
                temp_mayin_say++;
            }
        }
    }

    public void oyun_alanini_yazdir(){}

    public void run(){
        alan_boyutu_al();
        array_olustur_mayinlari_yerlestir();
        /*
        oyun_alanini_yazdir();
        while ((isWin = false) && (isLose = false)){
            hamle_al();
            hamle_yap(); kayıp kazanç kontrolü ve oyun alanını yazdırma burada yapılabilir.
            oyun_alanini_yazdir();
            kaybetti_mi();
            kazandi_mi();*/
        }
    }
