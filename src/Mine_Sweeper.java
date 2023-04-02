
import java.util.Scanner;
import java.util.Random;

public class Mine_Sweeper {
    //Değişkenleri burada tanımladım.
    int en = 0, boy = 0;
    boolean isWin = false, isLose = false;
    String[][] oyunalani;
    String[][] mayinlar;


    //Burada hata kontrolüde yaparak kullanıcıdan alan genisliği bilgisini alacağım.
    public void alan_boyutu_al() {
        Scanner input = new Scanner(System.in);
        //Burada en değerini hata kontrolü yaparak aldım.
        boolean isEnValid = false, isBoyValid = false;
        do {
            System.out.print("Alanın en degeri(3-30):");
            try {
                this.en = input.nextInt();
                if (this.en >= 3 & this.en <= 30) {
                    isEnValid = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lütfen 3-30 arasında bir tamsayı giriniz....");
                input.next();
            }
        } while (!isEnValid);

        //burada boy değerini hata kontrolü yaparak aldım.
        do {
            System.out.print("Alanın boy degeri(3-24):");
            try {
                this.boy = input.nextInt();
                if (this.boy >= 3 & this.boy <= 24) {
                    isBoyValid = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lütfen 3-24 arasında bir tamsayı giriniz....");
                input.next();
            }
        } while (!isBoyValid);
    }

    public void array_olustur_mayinlari_yerlestir() {
        Random random = new Random();
        int mayin_say = Math.floorDiv(en * boy, 4), temp_mayin_say = 0;
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
        //Deneme turu için mayınların konumunu yazdırdım.
        for(int i = 0;i<this.boy;i++){
            for(int b = 0;b<this.en;b++){
                System.out.print(mayinlar[i][b]+"|");
            }
            System.out.println();
        }
    }

    public void oyun_alanini_yazdir() {
        //Burada oyuncunun göreceği oyun alanını yazdıran metot tanımladım.
        for (int satir = 0; satir < oyunalani.length; satir++) {
            for (int sutun = 0; sutun < oyunalani[satir].length; sutun++) {
                System.out.print(oyunalani[satir][sutun] + " ");
            }
            System.out.println();
        }
    }

    public void hamle_al_yap() {
        //Bu metot önce doğru değerlerde hamle alacak daha sonra bu hamleyi gerçekleştirecek.
        Scanner input = new Scanner(System.in);
        int satir_index = 0, sutun_index = 0;
        boolean isSatirValid = false, isSutunValid = false;

        //satır_indexini hata konrolü yaparak aldım.
        do {
            System.out.print("Satır giriniz: ");
            try {
                satir_index = input.nextInt() - 1;
                if (satir_index >= 0 & satir_index <= this.oyunalani.length) {
                    isSatirValid = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.printf("Lütfen 1-%d arasında tamsayı giriniz.\n", this.oyunalani.length);
                input.next();
            }
        } while (!isSatirValid);

        //sutun indexini hata kontrolü yaparak aldım.
        do {
            System.out.print("Sutun giriniz: ");
            try {
                sutun_index = input.nextInt() - 1;
                if (sutun_index >= 0 & sutun_index <= this.oyunalani[satir_index].length) {
                    isSutunValid = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.printf("Lütfen 1-%d arasında tamsayı giriniz.\n", this.oyunalani[satir_index].length);
                input.next();
            }
        } while (!isSutunValid);

        //burada hamle yapılan konumda mayın kontrolü yapıyorum. Varsa isLose değeri true olacak. Yoksa kutu etrafındaki
        // mayınlar sayıalcak ve o kutuya değer yazıalcak.
        if (mayinlar[satir_index][sutun_index].equals("*")) {
            oyunalani[satir_index][sutun_index] = "*";
            this.isLose = true;
        } else {
            int cevre_mayin_say = 0;
            //Burada hamle yapılan kutunun 9 tarafındaki mayınlar kontrol edilecek.
            try {//sol üst köse.
                if (mayinlar[satir_index - 1][sutun_index - 1].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//üst orta.
                if (mayinlar[satir_index - 1][sutun_index].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//sağ üst köse.
                if (mayinlar[satir_index - 1][sutun_index + 1].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//sol alt köse.
                if (mayinlar[satir_index + 1][sutun_index - 1].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//alt orta.
                if (mayinlar[satir_index + 1][sutun_index].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//sağ alt köse.
                if (mayinlar[satir_index + 1][sutun_index + 1].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//sol kenar orta.
                if (mayinlar[satir_index][sutun_index - 1].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            try {//sağ kenar orta.
                if (mayinlar[satir_index][sutun_index + 1].equals("*")) {
                    cevre_mayin_say++;
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }

            if (cevre_mayin_say == 0) {
                this.oyunalani[satir_index][sutun_index] = " ";
            } else {
                this.oyunalani[satir_index][sutun_index] = String.valueOf(cevre_mayin_say);
            }

        }


    }

    public void kazandi_mi(){
        //Bu metot oyuncunun oyunu kazanıp kazanmadığını kontrol ediyor. Bütün açılan kutular sayılıp count
        // degiskeninde toplanıyor, eğer toplam bosluk sayısını içeren bosluk_say degiskenine esitse oyumcu oyunu
        //kazanıyoe ve isWin değikeni true oluyor.
        int count=0,mayin_say = Math.floorDiv(this.en * this.boy, 4),bosluk_say = (this.en*this.boy)-mayin_say;

        for (int satir_in = 0;satir_in< this.oyunalani.length;satir_in++){
            for(int sutun_in = 0;sutun_in<this.oyunalani[satir_in].length;sutun_in++){
                if(!this.oyunalani[satir_in][sutun_in].equals("*")&!this.oyunalani[satir_in][sutun_in].equals("-")){
                    count++;
                }
            }
        }

        if(bosluk_say == count){
            this.isWin = true;
        }

    }

    public void run() {
        alan_boyutu_al();
        array_olustur_mayinlari_yerlestir();
        System.out.println("Mayın Tarlası oyununa hoşgeldiniz...");
        oyun_alanini_yazdir();

        while ((!this.isWin) && (!this.isLose)) {
            hamle_al_yap(); //burada hamleyi doğru şekilde alıp
            oyun_alanini_yazdir();
            if(isLose){
                System.out.print("Kaybettiniz....");
            } else {
                kazandi_mi();
                if(isWin){
                    System.out.print("Tebrikler kazandınız....");
                }
            }
        }
    }
}

