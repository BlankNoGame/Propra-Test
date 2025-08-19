package de.hhu.propra.streams;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class Punkte {
    private static class Klausurergebnis {
        // Interessant sind die öffentlichen Methoden, die ganaue Implementierung ist für heute egal.
        private final String name;
        private final String klausur;
        private final OptionalDouble punkte;

        /**
         * @param teile Punktzahl in [0] (möglicherweise ""), Name in [1], Klausurname in [2] (möglicherweise "" oder Index nicht vorhanden)
         */
        public Klausurergebnis(String[] teile) {
            if (teile[0].isEmpty()) {
                this.punkte = OptionalDouble.empty();
            } else {
                this.punkte = OptionalDouble.of(Double.parseDouble(teile[0]));
            }
            this.name = teile[1];
            if(teile.length >= 3 && !teile[2].isEmpty()) {
                this.klausur = teile[2].replace(":", "");
            } else {
                this.klausur = null;
            }
        }

        public String getName() {
            return name;
        }

        /**
         * @return "hauptklausur", "nachklausur" oder Optional.Empty
         */
        public Optional<String> getKlausur() {
            return Optional.ofNullable(klausur);
        }

        public OptionalDouble getPunkte() {
            return punkte;
        }
    }

    public static void main(String[] args) {
        final List<String> zeilen = klausurdatenCsvEinlesen(); // liest alle Zeilen der CSV-Datei in eine Liste ein
        int count = 0 ;

        // gibt die Menge von Eingelesenen Zeilen an
        for(String i: klausurdatenCsvEinlesen()) {
            count++;
        }
        System.out.println(count);

        //Hier wird der entstandene Stream in eine Liste von Typ Klausurergebnis gespeichert, damit man diese in späteren Aufgaben nutzen kann ohnen immer den selben Stream zu öffnen
        List<Klausurergebnis> klausurergebnisse = zeilen.stream()
            .skip(1)
            .map(s -> s.split(","))
            .map(Klausurergebnis::new)
            .toList();

        //wir nutzen die gerade erstelle Liste von Klausurergebnissen um daraus wieder einen Stream zu bekommen und von diesem die Namen auszugeben    
//        klausurergebnisse.stream()
//            .map(Klausurergebnis::getName)  //ich denke mal wir kriegen hiermit von jedem Klausurergebnis Objekt in der Liste den Namen zurück
//           .forEach(System.out::println);  // und printen den aus

        klausurergebnisse.stream()
            .limit(5)
            .map(Klausurergebnis::getName)
            .forEach(System.out::println);

        System.out.println(klausurergebnisse.stream() // Wichtig hierbei ist dass die System.out Methode den ganzen Stream umschließt, sonst kommt nicht das gewünschte ergebnis
            .map(Klausurergebnis::getPunkte)
            .filter(OptionalDouble::isPresent)
            .filter(punkte -> punkte.getAsDouble() >= 100)
            .count());

        /*klausurergebnisse.stream() 
            .filter(klausurergebnis -> klausurergebnis.getPunkte().orElse(0) >= 100)
            .map(Klausurergebnis::getName)
            .forEach(System.out::println);
            */

        System.out.println(klausurergebnisse.stream() // Wichtig hierbei ist dass die System.out Methode den ganzen Stream umschließt, sonst kommt nicht das gewünschte ergebnis
            .map(Klausurergebnis::getPunkte)
            .filter(OptionalDouble::isPresent)
            .filter(punkte -> punkte.getAsDouble() < 50)
            .count());        


        System.out.println(klausurergebnisse.stream() // Wichtig hierbei ist dass die System.out Methode den ganzen Stream umschließt, sonst kommt nicht das gewünschte ergebnis
            .filter(klausurergebnis -> klausurergebnis.getKlausur().orElse(" ").equals("hauptklausur"))
            .count());    
        

        // für den nächsten Teil der Aufgabe: uebungsdatenEinlesen();
    }

    private static List<String> klausurdatenCsvEinlesen() {
        // liest man normalerweise aus einer Datei, ums für jetzt einfacher zu machen, schreiben wir den Dateiinhalt direkt hier in den Code.
        // return Files.readAllLines(Paths.get("punkte.csv")); // liest alle Zeilen der Datei in eine Liste ein
        // Mit """ können wir mehrzeilige Strings, die Zeilenumbrüche enthalten, erstellen (sog. Text Block). Schauen
        // wir uns auf einem späteren Wochenblatt noch an.
        return """
                summe-klausur-punkte,name,klausur
                87,Ender,:hauptklausur
                80,Gennadi,:hauptklausur
                ,Leopold-Max,
                ,Dragina,
                ,Cvijo,
                82,Rahima,:hauptklausur
                ,Marc-Andre,
                70,Samra,:hauptklausur
                ,Gerlo,
                ,Irmfried,
                73,Loretta,:hauptklausur
                68,Jans,:hauptklausur
                2,Gefion,:hauptklausur
                18,Herli,:hauptklausur
                48,Ronen,:hauptklausur
                ,Kent,
                80,Giuliana,:hauptklausur
                ,Lepa,
                ,Shamim,
                87,Nicka,:hauptklausur
                ,Sacha,
                55,Nessis,:hauptklausur
                96,Roso,:hauptklausur
                ,Harilos,
                ,Cleonora,
                68,Nikolaidis,:hauptklausur
                65,Blandine,:hauptklausur
                49,Penny,:hauptklausur
                ,Nacige,
                83,Zuorc,:hauptklausur
                ,Claudina,
                ,Kamiran,
                ,Xavier,
                ,Mehmet-Emin,
                105,Salam,:hauptklausur
                ,Elvi,
                ,Gonda,
                ,Rastodev,
                1,Margaretha-Christina,:nachklausur
                ,Wigand,
                ,Acimovic,
                ,Sulamith,
                29,Franz-Peter,:hauptklausur
                50,Uzer,:hauptklausur
                ,Karl-Josef,
                ,Milovanovitc,
                79,Effichio,:hauptklausur
                1,Vojin,:hauptklausur
                ,Zirko,
                76,Hamide,:hauptklausur
                ,Ekkart,
                ,Abderrazak,
                ,Miroslaw,
                59,Kaissa,:hauptklausur
                43.5,Edda,:nachklausur
                ,Hans-Ulrich,
                85.5,Jildiray,:nachklausur
                ,Jurai,
                ,Pejdo,
                69,Innocenzo,:hauptklausur
                81.0,Joschi,:nachklausur
                28,Nurel,:hauptklausur
                90,Memsi,:hauptklausur
                ,Thera,
                ,Carl-Joachim,
                ,Mofid,
                68,Mohamed,:hauptklausur
                ,Ruven,
                ,Bogdan-Catalin,
                ,Perran,
                68,Kaouther,:hauptklausur
                ,Helfried,
                ,Milada-Edith,
                85,Memic,:hauptklausur
                92,Teresija,:hauptklausur
                ,Esat,
                39,Benedetto,:hauptklausur
                ,Jean-Loup,
                ,Mosayeb,
                90,Ursel,:hauptklausur
                82,Emanuil,:hauptklausur
                ,Herta,
                ,Tranoylidis,
                99,Chirila,:hauptklausur
                74,Christen,:hauptklausur
                ,Lazaridov,
                ,Gultekin,
                53,Angeline,:hauptklausur
                ,Erltraud,
                ,Anne-Christine,
                ,Marlis,
                66,Ovidiu-Gigi,:hauptklausur
                ,Marie-Francoise,
                ,Liesa,
                80.0,Selcuk,:nachklausur
                0,Dettmar,:nachklausur
                ,Baby,
                16,Loris,:hauptklausur
                63,Kasaraporn,:hauptklausur
                81,Kristallia,:hauptklausur
                ,Hans-Albert,
                88,Hedic,:hauptklausur
                1,Kuddusi,:hauptklausur
                2,Stojce,:hauptklausur
                ,Talal,
                99,Arlette,:hauptklausur
                90,Hanije,:hauptklausur
                80.5,Cuturilo,:nachklausur
                48.5,Gebesce,:nachklausur
                21.0,Rebe,:nachklausur
                ,Sahim,
                ,Saturnino,
                ,Sigurd,
                56.0,Claus-Jochen,:nachklausur
                ,Geoffrey,
                ,Franck,
                49.0,Chanin,:nachklausur
                ,Musa,
                ,Lydia,
                77,Rai,:hauptklausur
                82,Ascantbrk,:hauptklausur
                61,Rosemari,:hauptklausur
                ,Riza,
                95,Ranji,:hauptklausur
                85,Mohammed-Aref,:hauptklausur
                31,Heimber,:hauptklausur
                ,Dogun,
                80,Ayneci,:hauptklausur
                ,Halilic,
                38,Allice,:hauptklausur
                66,Davlas,:hauptklausur
                0,Abdul-Wahid,:hauptklausur
                ,Adalbert-August,
                ,Solvieg,
                2,Sahba,:hauptklausur
                88,Cikla,:hauptklausur
                ,Dolli,
                44.5,Yuko,:nachklausur
                ,Nani,
                ,Sabaheta,
                0,Arabel,:nachklausur
                33,Vinko,:hauptklausur
                96,Wilmut,:hauptklausur
                ,Agness,
                ,Mechtilde,
                ,Margot-Luise,
                ,Appostolos,
                50,Miriam,:hauptklausur
                ,Analiza,
                ,Radic,
                ,Tonia,
                ,Guecueko,
                ,Gene,
                91,Agori,:hauptklausur
                ,Zhaoyang,
                55,Sidirula,:hauptklausur
                ,Fajlija,
                79,Hvistos,:hauptklausur
                ,Megumi,
                28,Yauw,:hauptklausur
                ,Torben,
                ,Gerwich,
                62,Telemach,:hauptklausur
                ,Frodo,
                ,Ilic,
                41,Ian,:hauptklausur
                30,Gonz,:hauptklausur
                41,Sibylla,:hauptklausur
                ,Damali,
                ,Hedva,
                63,Joumana,:hauptklausur
                ,Soheil,
                ,Naee,
                106,Chang-Woon,:hauptklausur
                48,Mal,:hauptklausur
                6,Markos,:hauptklausur
                52.0,Rosamunde,:nachklausur
                78.5,Gharibzadeh,:nachklausur
                ,Hans-Rolf,
                65,Teodoro,:hauptklausur
                ,Odoardo,
                76,Ritza,:hauptklausur
                ,Kathar,
                ,Caline,
                1,Kada,:hauptklausur
                ,Hedieh,
                ,Veliatin,
                ,Yvette,
                ,Odysseus,
                ,Simela,
                93,Ileana-Codrutza,:hauptklausur
                ,Ervina,
                23,Felicitas,:hauptklausur
                ,Yerl,
                ,Luana,
                ,Eleiane,
                ,Tam,
                ,Vasili,
                59,Phili,:hauptklausur
                80,Glen,:hauptklausur
                1,Mammut,:nachklausur
                ,Fania,
                ,Carin,
                61,Ntousia,:hauptklausur
                ,Nihada,
                88,Vitalis,:hauptklausur
                79,Hermine-Zita,:hauptklausur
                ,Kornelia,
                ,Elf,
                ,Arokiam,
                55,Hertha,:hauptklausur
                ,Seniha,
                10.0,Carl-Otto,:nachklausur
                50,Doma,:hauptklausur
                104,Ashok,:hauptklausur
                76,Miloranka,:hauptklausur
                46,Crnoja,:hauptklausur
                ,Beate-Maria,
                ,Ahrend,
                ,Babak,
                89,Malak,:hauptklausur
                108,Nadejda,:hauptklausur
                51,Synthia,:hauptklausur
                ,Tivador,
                ,Alfina,
                88,Dorin,:hauptklausur
                ,Shahnaz,
                ,Sernin,
                20.0,Melania,:nachklausur
                108,Alaattin,:hauptklausur
                39,Lale,:hauptklausur
                96,Cathri,:hauptklausur
                ,Octavian,
                ,Euan,
                ,Hanns-Heinz,
                70,Veselj,:hauptklausur
                ,Samaraweera,
                97,Vuleta,:hauptklausur
                100,Fahredin,:hauptklausur
                ,Boonma,
                ,Annabelle,
                2,Lubos,:hauptklausur
                74,Ahma,:hauptklausur
                104,Rosemary,:hauptklausur
                1,Gil,:nachklausur
                ,Srdic,
                ,Slavisa,
                ,Kurt-Alexander,
                ,Ismini,
                95,Friedrich-Wilhelm,:hauptklausur
                ,Olf-Winand,
                73,Berndt,:hauptklausur
                104,Pesic,:hauptklausur
                ,Eller,
                106,Benita,:hauptklausur
                ,Egid,
                ,Dietz-Dieter,
                ,Menelaos,
                ,Budes,
                65,Ramaza,:hauptklausur
                ,Vitalis,
                ,Paraschiva,
                ,Minka,
                ,Eginhard,
                ,Dorita,
                25,Rahime,:hauptklausur
                ,Nikolina,
                40.5,Sebas,:nachklausur
                ,Koloman,
                71,Nadid,:hauptklausur
                ,Perka,
                ,Alav,
                ,Coralic,
                ,Resmija,
                ,Tarzan,
                ,Eug,
                ,Weishi,
                ,Elise,
                ,Godwin,
                93,Paco,:hauptklausur
                ,Ernestine,
                94,Jonny,:hauptklausur
                ,Zrinka,
                ,Rowland,
                ,Naciye,
                ,Sharloot,
                ,Quyet,
                ,Erns,
                ,Nadi,
                ,Lioba,
                96,Oswald,:hauptklausur
                97,Lona,:hauptklausur
                47,Vural,:hauptklausur
                ,Nazir,
                ,Kazumichi,
                74,Amanullah,:hauptklausur
                ,Hosep,
                0,Muo,:nachklausur
                78,Katovcic,:hauptklausur
                ,Salman,
                ,Nurit,
                51,Wenelin,:hauptklausur
                ,Jannetje,
                ,Caracciolo,
                ,Lourdes,
                ,Dimitrias,
                ,Vuceta,
                74.5,Milos,:nachklausur
                0.0,Joachim,:nachklausur
                19.0,Abderrahmne,:nachklausur
                47.0,Ehrengard,:nachklausur
                ,Reinhold,
                58,Kiriaki,:hauptklausur
                90,Zelka,:hauptklausur
                ,Kurto,
                ,Ingetraud,
                87,Gusinjac,:hauptklausur
                67,Emanuel,:hauptklausur
                ,Ted,
                ,Hyunjoo,
                ,Hastor,
                59,Algis,:hauptklausur
                1,Saviour,:nachklausur
                1,Vorgic,:hauptklausur
                ,Georgis,
                ,Jael,
                ,Hilke,
                ,Kottias,
                1,Elda,:hauptklausur
                ,Eve-Maria,
                76,Yolcu,:hauptklausur
                ,Janusz,
                ,Luci,
                ,Roberte,
                ,Adina,
                ,Enda,
                ,Koshun,
                ,Djulic,
                ,Martine,
                ,Gheza,
                ,Carl-Joachim,
                105,Soleiman,:hauptklausur
                102,Nagdi,:hauptklausur
                ,Claude-France,
                ,Nguyen-dinh,
                102,Biliana,:hauptklausur
                25,Branislava,:hauptklausur
                63,Nurhan,:hauptklausur
                74,Baratali,:hauptklausur
                ,Vazrik,
                2,Elisabet,:hauptklausur
                ,Julchen,
                ,Hans-Jakob,
                84,Burgl,:hauptklausur
                ,Raimund,
                ,Sadika,
                ,Wella,
                30,Yeuda,:hauptklausur
                76,Christophoros,:hauptklausur
                ,Angelika,
                ,Karl-Albert,
                ,Fengzhi,
                102,Slim,:hauptklausur
                ,Janusz,
                69,Zilla,:hauptklausur
                48,Biljana,:hauptklausur
                34,Alaaeldin,:hauptklausur
                97,Akihiro,:hauptklausur
                104,Farudin,:hauptklausur
                ,Aurikel,
                ,Lambert,
                ,Redzepi,
                ,Pankraz,
                62.0,Ehsan,:nachklausur
                ,Manue,
                ,Rolf-Ulrich,
                91,Alexan,:hauptklausur
                20,Umit,:hauptklausur
                21.0,Sidero,:nachklausur
                ,Benedicta,
                75,Gadzo,:hauptklausur
                ,Eskil,
                ,Mahmooo,
                93,Osaj,:hauptklausur
                ,Kesedic,
                86,Alexander-Sascha,:hauptklausur
                67,Karlpeter,:hauptklausur
                52,Mathias-Benno,:hauptklausur
                56,Sera,:hauptklausur
                ,Nenadl,
                ,Maximi,
                67,Machray,:hauptklausur
                ,Fausto,
                49,Antonio-Omar,:hauptklausur
                ,Maxwell,
                ,Sheela,
                ,Grietje,
                71,Jonja,:hauptklausur
                ,Jenny,
                ,Silvie,
                ,Husnija,
                91,Bergit,:hauptklausur
                ,Zsuzsanna,
                ,Fekry,
                82,Ponnuthurai,:hauptklausur
                ,Lynne,
                89,Ivanka,:hauptklausur
                0,Madalina,:nachklausur
                ,Atilgan,
                23.0,Hamit,:nachklausur
                12,Elenita,:hauptklausur
                84.0,Karl-Erich,:nachklausur
                ,Bouraoui,
                68,Aja,:hauptklausur
                ,Git,
                ,Chidlia,
                ,Bernhard-Michael,
                ,Karl-Eduard,
                ,Chrisula,
                ,Neli,
                48.0,Klausrainer,:nachklausur
                ,Wasil,
                ,Vincenz,
                39,Stahman,:hauptklausur
                ,Thilp,
                54.0,Gesine,:nachklausur
                ,Nedio,
                ,Alun,
                ,Aliahmad,
                41.0,Nadid,:nachklausur
                42,Okcana,:hauptklausur
                ,Gurda,
                ,Maruan,
                1,Anna-Magdalena,:hauptklausur
                79,Dragidela,:hauptklausur
                ,Ronnie,
                102,Khalid,:hauptklausur
                ,Agatha,
                ,Marinela,
                ,Mihriban,
                ,Mareija,
                ,Kraswiqi,
                72,Vukadin,:hauptklausur
                ,Mathild,
                69,Gerda,:hauptklausur
                ,Salih,
                ,Markulin,
                ,Poldina,
                ,Hassouna,
                ,Dave,
                ,Memsi,
                ,Ilekra,
                ,Sevindik,
                42.5,Yaron,:nachklausur
                12,Drace,:hauptklausur
                95,Stje,:hauptklausur
                ,Fred,
                64,Alenka,:hauptklausur
                ,Marinkosric,
                45.5,Dafne,:nachklausur
                ,Tizia-Berit,
                58,Stantcho,:hauptklausur
                ,Rebija,
                ,Enge,
                ,Margitta-Janine,
                66.5,Nemeth,:nachklausur
                ,Vilja,
                ,Emmett,
                79,Hillar,:hauptklausur
                ,Colmar,
                ,Lubos,
                ,Gerhild,
                49,Hannskarl,:hauptklausur
                ,Isa-Karin,
                32,Herman,:hauptklausur
                ,Karl-Werner,
                ,Lisac,
                76,Guoc,:hauptklausur
                ,Hedija,
                ,Abderrahmne,
                1,Culus,:hauptklausur
                39,Nezaket,:hauptklausur
                46.5,Marlei,:nachklausur
                ,Behcet,
                92,Antoniette,:hauptklausur
                ,Maria-Therese,
                ,Abdallah,
                ,Juta,
                2,Peter-Pal,:hauptklausur
                ,Rudolph,
                41,Vittorina,:hauptklausur
                ,Ingo-Julius,
                ,Jasminka,
                96,Emo,:hauptklausur
                ,Ingri,
                ,Jamil,
                85,Benno-Heinz,:hauptklausur
                70,Ertunay,:hauptklausur
                ,Attiogbe,
                86,Veliatin,:hauptklausur
                ,Serket,
                61.0,Mads,:nachklausur
                93,Antonios,:hauptklausur
                ,Sejfudiu,
                ,Bozena,
                43,Husniye,:hauptklausur
                ,Salouha,
                ,Snezana,
                106,Terttu,:hauptklausur
                ,Regine,
                ,Lutfi,
                ,Stoitscho,
                81.0,Georgijeva,:nachklausur
                50.5,Neat,:nachklausur
                ,Rago,
                ,Wulf-Dedo,
                81,Karina,:hauptklausur
                35,Philippa,:hauptklausur
                ,Jean-Francois,
                ,Gesetha,
                ,Alil,
                ,Vaja,
                102,Garaj,:hauptklausur
                ,Menko,
                ,Traude,
                ,Egyed,
                39.5,Myhankhah,:nachklausur
                85,Atossa,:hauptklausur
                ,Kiligaslan,
                87.0,Utho,:nachklausur
                ,Refik-Fikret,
                67,Carme,:hauptklausur
                21,Uwe-Kersten,:hauptklausur
                ,Drazan,
                ,Genet,
                ,Hanno,
                81,Chrisanthi,:hauptklausur
                ,Vajk,
                79.0,Quirino,:nachklausur
                ,Ermeh,
                83,Anna-Maria,:hauptklausur
                61,Karen-Maria,:hauptklausur
                ,Magsud,
                ,Yenal,
                ,Nagdi,
                ,Carl-Otto,
                29,Adeltraud,:hauptklausur
                ,Tadisa,
                97,Birsel,:hauptklausur
                ,Demetra,
                69,Bernd-Ingolf,:hauptklausur
                51,Paulheinz,:hauptklausur
                35,Lott,:hauptklausur
                ,Spencer,
                24,Ugur,:hauptklausur
                ,Agathe,
                ,Emmanuel,
                ,Prisana,
                ,Priscila,
                56,Rosia,:hauptklausur
                ,Vendel,
                ,Fakidaris,
                27.0,Ines,:nachklausur
                ,Ghadieh,
                ,Achmet,
                ,Voskos,
                ,Jefrem,
                ,Magrit,
                ,Sylvia,
                2,Safa,:hauptklausur
                ,Bronko,
                ,Sab,
                ,Segiye,
                66,Kurti,:hauptklausur
                75,Erdog,:hauptklausur
                96,Zineb,:hauptklausur
                ,Myhankhah,
                ,Fahrittin,
                70.5,Hamzic,:nachklausur
                ,Bjelic,
                ,Jia-Ling,
                1,Loria,:hauptklausur
                ,Donce,
                ,Traudel,
                ,Nikolic,
                ,Srboljub,
                ,Thorsteinn,
                ,Malenica,
                ,Irmingart,
                2,Heilwig,:hauptklausur
                ,Aden,
                36,Georgij,:hauptklausur
                85,Agnes,:hauptklausur
                ,Draganco,
                59,Jurai,:hauptklausur
                ,Zef,
                1,Tumpa,:hauptklausur
                ,Ingried,
                ,Thusnelda,
                ,Kyrill,
                ,Vassili,
                ,Tona,
                ,Hanspeter,
                ,Deze,
                ,Sharad,
                ,Sue,
                22,Friederi,:hauptklausur
                ,Tilman,
                ,Djordjo,
                79,Harty,:hauptklausur
                37.0,Markward,:nachklausur
                ,Margrid,
                ,Zivadin,
                ,Ubald,
                41,Wahidullah,:hauptklausur
                57,John-Christian,:hauptklausur
                ,Marielouise,
                23.0,Marlitt,:nachklausur
                ,Jovanovic,
                ,Walpurga,
                ,Rene,
                ,Keyvan,
                0,Ricki,:hauptklausur
                ,Gudrun,
                ,Abdulgani,
                73,Kevser,:hauptklausur
                ,Serket,
                ,Dusanca,
                96,Richard-Konstantin,:hauptklausur
                ,Irmi,
                77,Belen,:hauptklausur
                65,Ling-Sheng,:hauptklausur
                ,Senahid,
                ,Behrooz,
                55,Babur,:hauptklausur
                ,Rolf-Eckard,
                ,Safaric,
                ,Ludwiga,
                ,Chokouh,
                78,Zivorad,:hauptklausur
                63,Seraphima,:hauptklausur
                94,Romeo,:hauptklausur
                ,Jens,
                ,Nedzib,
                ,Milly,
                83,Manh-Kha,:hauptklausur
                ,Akzoy,
                ,Wulfo,
                ,Wagen,
                ,Ciril,
                80,Malesevic,:hauptklausur
                63,Li-We,:hauptklausur
                ,Asl,
                ,Gintaras,
                ,Aletta,
                79,Margrid,:hauptklausur
                91,Thussi,:hauptklausur
                ,Anete,
                ,Kocu,
                ,Imer,
                ,Zaman,
                71,Azis,:hauptklausur
                ,Feride,
                ,Chrysanth,
                0,Dola,:hauptklausur
                ,Kazuhito,
                ,Ralp,
                99,Rica-Andreas,:hauptklausur
                33,Daliah,:hauptklausur
                73.5,Jadranko,:nachklausur
                47.5,Jan-Stuart,:nachklausur
                ,Waslaw,
                0.0,Hevzi,:nachklausur
                ,Korbi,
                ,Jean-Marie,
                ,Stoian,
                ,Khobalotte,
                ,Noorali,
                ,Findik,
                84,Philip,:hauptklausur
                ,Silvija,
                ,Margre,
                ,Gerda-Ruth,
                ,Shehata,
                ,Tihomir,
                ,Betin,
                44,Erdin,:hauptklausur
                12,Yavuzatmaca,:hauptklausur
                ,Vito,
                ,Vollert,
                ,Melazim,
                ,Acimovic,
                16.0,Vivek,:nachklausur
                ,Havaana,
                56,Carlos-Manuel,:hauptklausur
                ,Zafa,
                ,Gundekar,
                ,Elsa,
                58,Zivadin,:hauptklausur
                ,Sirko,
                27.0,Rudolfa,:nachklausur
                1,Alona,:hauptklausur
                ,Hasani,
                106,Joelle,:hauptklausur
                96,Osmo,:hauptklausur
                15,Todosijo,:hauptklausur
                37.0,Venica,:nachklausur
                ,Giusseppe,
                ,Bubalo,
                96,Svetlana,:hauptklausur
                50,Frizz,:hauptklausur
                ,Slavoljub,
                101,Xaver,:hauptklausur
                75,Leonidos,:hauptklausur
                ,Raffaela,
                ,Sofi,
                42,Peter-Klaus,:hauptklausur
                ,Kaye,
                ,Trudbert,
                ,Tamama,
                ,Rizzo,
                85,Lykke,:hauptklausur
                81,Pajaziti,:hauptklausur
                76,Greteline,:hauptklausur
                ,Rolf-Eckard,
                ,Ernst-Georg,
                33.5,Katharina-Justina,:nachklausur
                1,Heinz,:hauptklausur
                1,Toba,:hauptklausur
                ,Ted,
                ,Rubyana,
                100,Karmen,:hauptklausur
                ,Dobrila,
                ,Aichin,
                50,Soudabeh,:hauptklausur
                ,Almas,
                61,Dimostenis,:hauptklausur
                ,Sadiye,
                104,Heinz-Helmut,:hauptklausur
                ,Fritz-Wilhelm,
                83,Mujesira,:hauptklausur
                ,Wassilios,
                ,Rud,
                ,Neobo,
                60,Lee,:hauptklausur
                72,Kolona,:hauptklausur
                47,Na,:hauptklausur
                ,Uidic,
                61,Tuncay,:hauptklausur
                46,Harwin,:hauptklausur
                ,Zelka,
                ,Alessandra,
                ,Koprena,
                ,Kaltek,
                ,Jason,
                ,Neriman,
                ,Rica-Andreas,
                94,Sonja-Christa,:hauptklausur
                ,Erszebet,
                ,Euphemie,
                16,Gerd-Focken,:hauptklausur
                ,Shahin,
                83,Jozsef,:hauptklausur
                97,Ute,:hauptklausur
                111,Tom-Eric,:hauptklausur
                45.5,Mancic,:nachklausur
                ,Tilli,
                ,Siemin,
                ,Gundhild,
                ,Fotini,
                ,Raba,
                ,Birben,
                ,Fadoua,
                ,Yvonne,
                40,Siglind,:hauptklausur
                ,Alia,
                37,Lisbet,:hauptklausur
                34,Constanza,:hauptklausur
                60.0,Efdimios,:nachklausur
                ,Jure,
                ,Slamat,
                ,Theocharis,
                ,Barjam,
                78,Ankica,:hauptklausur
                54,Huu-Hieu,:hauptklausur
                60,Nurbiye,:hauptklausur
                52,Fihreto,:hauptklausur
                ,Frith,
                101,Vitola,:hauptklausur
                94,Mirsini,:hauptklausur
                ,Josifine,
                ,Not,
                ,Dulaga,
                84,Azim,:hauptklausur
                52,Franz-Josef,:hauptklausur
                ,Radali,
                ,Alfrie,
                ,Dieter-Benno,
                78,Garbis,:hauptklausur
                ,Herberth,
                ,Kaveh,
                ,Vasenka,
                """.lines().toList();
    }

    private static List<String> uebungsdatenEinlesen() {
        // Gehen Sie mal davon aus, dass jede Person nur einen Vornamen hat.
        return """
                Solmazgen Sand Mathematik 444 69%
                Klawdia Hülscher Informatik 0 0%
                Felix-Hubert Hirschmann Informatik 399.5 62%
                Rosa-Maria Strube Informatik 356 56%
                Frane Mohr Informatik 178 28%
                Berta-Theresia Türk Informatik 405 63%
                Grazyna Krohn Informatik 90 14%
                Violeta Sowa Informatik 266.5 42%
                Wilfrie Breidenbach Informatik 454.5 71%
                Nikos Pawlik Informatik 289 45%
                Bugra Kube Informatik 360 56%
                Enrica Heitmann Informatik 60 9%
                Numan Bork Informatik 333 52%
                Ralph-Günther Nowak Informatik 362 57%
                Atsuo Thoma Informatik 27 4%
                Saif Reis Informatik 254.5 40%
                Philippa Gürner Anderer 0 0%
                Tünde-Agata Scherf Informatik 379 59%
                Abdullahm Piontek Informatik 110 17%
                Hortensia Kretzer Mathematik 108 17%
                Erasmus Lühr Informatik 447.5 70%
                Lotife Locher Informatik 0 0%
                Cambel Hinzmann Informatik 439.5 69%
                Kadric Mahnke Informationswissenschaften 350.5 55%
                Hajro Pfisterer Informatik 442.5 69%
                Djura Helms Informatik 76 12%
                Aydin Reber Informationswissenschaften 228 36%
                Sina Bartels Informatik 103 16%
                Vase Hallmann Informatik 145 23%
                Gabri Sieg Informationswissenschaften 461.5 72%
                Lobina Kiefer Informatik 326.5 51%
                Graziano Ruppert Informatik 378 59%
                Rappazzo Strauss Informatik 94.5 15%
                Svetosar Raschke Informationswissenschaften 374 58%
                Reka Pietsch Informatik 27 4%
                Lilib Ackermann Anderer 270.5 42%
                Senol Jükel Informationswissenschaften 311.5 49%
                Maria-Angela Holzapfel Informatik 246 38%
                Ribana Salewski Informatik 364 57%
                Hildebrecht Eger Physik 533 83%
                Benedicte Burkhard Informatik 197.5 31%
                Emir Lehmann Physik 571 89%
                Vilja Mahnke Informatik 340 53%
                Godje Klier Informationswissenschaften 0 0%
                Lidya Habermann Informatik 0 0%
                Rafaela Hill Informatik 406 63%
                Keifi Wiebe Informationswissenschaften 49 8%
                Gertrude Haas Informatik 593.5 93%
                Theophanie Niklas Informatik 385 60%
                Claudine Strauch Informatik 355.5 56%
                Loukia Exner Informationswissenschaften 399.5 62%
                Janyl Tetzlaff Informationswissenschaften 284.5 44%
                Florentia Herwig Informationswissenschaften 357.5 56%
                Adjei Nagel Informatik 81 13%
                Dimitriy Schipper Informatik 608 95%
                Raptis Brettschneider Informationswissenschaften 360 56%
                Gabrio Maucher Informationswissenschaften 335.5 52%
                Gerhard-Markus Schwind Informationswissenschaften 428.5 67%
                Ron Wolters Informationswissenschaften 525 82%
                Lind Wiens Informatik 607.5 95%
                Loredana Grosch Informatik 504.5 79%
                Raila Siegmund Informationswissenschaften 119 19%
                Oscar Schuler Physik 137 21%
                Koukoutselas Dost Informatik 380 59%
                Kader Schübel Informatik 32 5%
                Tim-Peter Ross Mathematik 386.5 60%
                Detre Klingler Mathematik 41 6%
                Sloboda Holstein Physik 270 42%
                Josko Volz Physik 578.5 90%
                Ucar Wedel Physik 499 78%
                Leena Rüüner Anderer 111 17%
                Barthold Angerer Physik 307 48%
                Hans-Christof Patzer Informationswissenschaften 0 0%
                Johannes-Hermann Witzke Physik 420.5 66%
                Selig Reinhold Informatik 393.5 61%
                Ennia Christmann Informatik 112 18%
                Tors Schimpf Informatik 56 9%
                Ocalar Heck Physik 287 45%
                Sansone Hünig Physik 555.5 87%
                Kyra Heiland Informatik 165.5 26%
                Jozef Westhoff Anderer 72 11%
                Traudl Albers Informatik 557 87%
                Bejctzet Barz Informatik 352 55%
                Anna-Magdalena Rieü Informatik 331.5 52%
                Harma Handke Informatik 246 38%
                Angistalis Gutzeit Informatik 549.5 86%
                Ljerka Stutz Physik 577 90%
                Kolet Bühme Informatik 194 30%
                Zacharenia Rauh Informatik 34 5%
                Djamchid Pauls Physik 426.5 67%
                Sekic Trüster Informatik 40 6%
                Herberth-Lutz Büuerle Informationswissenschaften 88 14%
                Iain Kümmerer Mathematik 631 99%
                Gaspare Wagener Physik 351 55%
                Yaser Hübsch Physik 383.5 60%
                Dewa Clauüen Mathematik 396 62%
                Janet Dick Anderer 36 6%
                Ragmilla Reindl Mathematik 602 94%
                Wiwo Stratmann Informatik 449 70%
                Sabahudin Daub Informatik 205 32%
                Arcangelo Balke Informatik 76 12%
                Dominique Matzke Informatik 48 8%
                Agija Christmann Informatik 105 16%
                Anorthe Langen Informatik 237 37%
                Nanna Niebuhr Informatik 412 64%
                Stanisa Wrobel Informatik 116 18%
                Mustfa Nikolaus Informatik 482.5 75%
                Awi Brandner Informatik 308 48%
                Stanica Schiele Informatik 204 32%
                Karl-Heribert Rührig Informatik 495.5 77%
                Derick Augustin Informatik 284.5 44%
                Tri-Thang Neumann Physik 225 35%
                Niels-Wenno Gürtner Informatik 76 12%
                Xhevdet Eitel Informatik 593 93%
                Hani Hüls Mathematik 84 13%
                Bianca-Maria Neuner Informationswissenschaften 247 39%
                Konrosh Lüke Informatik 241 38%
                Valentine Wollmann Informationswissenschaften 142 22%
                Rogan Kunz Informatik 414.5 65%
                Majic Schlosser Anderer 0 0%
                Krasnici Aigner Informatik 200 31%
                Viviana Büumler Informatik 511 80%
                Muo Brüutigam Informationswissenschaften 145 23%
                Cakic Johannes Informatik 0 0%
                Eva-Marie Kirsch Informationswissenschaften 328 51%
                Marjorie Küppe Mathematik 0 0%
                Alvino Schultheis Informatik 533.5 83%
                Fatma Emmerich Physik 593 93%
                Bastasic Wehrmann Mathematik 359 56%
                Taleb Burkhard Informationswissenschaften 0 0%
                Ahnsun Ludwig Informatik 157.5 25%
                Hidayet Uhrig Informatik 326.5 51%
                Yousef Franzen Informatik 141 22%
                Ulrik Vogel Informatik 432.5 68%
                Eufenie Weingürtner Mathematik 535.5 84%
                Yilmaz Rosenkranz Informatik 100 16%
                Teddy Michaelis Informatik 97.5 15%
                Helgi Olbrich Informationswissenschaften 135 21%
                Gyula Pries Informatik 492 77%
                Seyedreza Knebel Anderer 0 0%
                Rafael Heine Informatik 190 30%
                Luminita Harth Informatik 172 27%
                Tünde-Agata Steinert Informatik 558.5 87%
                Stergiani Engelke Anderer 39 6%
                Barica Dahms Informatik 399 62%
                Sue Stolte Informationswissenschaften 282.5 44%
                Lionello Hüfler Informationswissenschaften 379 59%
                Vishwadeep Bach Informatik 477 75%
                Coryse Friedrich Informatik 0 0%
                Taylan Schrüer Informatik 550.5 86%
                Vijai Ludewig Informatik 514.5 80%
                Fevziye Kusch Informatik 40 6%
                Nalin Rieke Informatik 362 57%
                Veric Weyers Informatik 373 58%
                Heinrich-Otto Herter Informatik 524 82%
                Petrus Rick Informatik 403.5 63%
                Pala Scherer Informatik 596 93%
                Peter-Michael Schwartz Informationswissenschaften 260.5 41%
                Cecile Hasse Informatik 8 1%
                Benson Blümel Informatik 473 74%
                Ilyazi Holz Mathematik 185 29%
                Algieri Schreiber Informatik 396.5 62%
                Fedor Wicke Informatik 454.5 71%
                Agavni Brehmer Physik 370.5 58%
                Lenz Schiele Informatik 483.5 76%
                Ludwik Stuber Informatik 1 0%
                Abdessadeq Bornemann Informatik 402.5 63%
                Solreig Kost Informatik 120 19%
                Haslak Polzin Mathematik 359.5 56%
                Benedetto Schleicher Mathematik 522.5 82%
                Beniamino Haufe Informatik 161 25%
                Silva Katzer Mathematik 627 98%
                Catello Lüdemann Mathematik 554.5 87%
                Hüsegin Krüner Informatik 0 0%
                Ganna Jungbluth Informatik 484.5 76%
                Mazalica Gaüner Informatik 51 8%
                Marth Ritter Informatik 582 91%
                Saban Glüser Informatik 385 60%
                Irüne Neumeier Informatik 626.5 98%
                Arma Grieger Informatik 321 50%
                Soheyla Kratzer Informatik 46 7%
                Nafija Schroll Informatik 58 9%
                Tilechachos Spüth Informatik 516 81%
                Kaevan Rutz Informatik 317.5 50%
                Moszek Schieül Informatik 514 80%
                Pepi Kurz Informatik 432.5 68%
                Fenni Reichardt Informatik 415.5 65%
                Celeste Münch Mathematik 524 82%
                Ognjen Acker Informatik 539 84%
                Mesut Eckhoff Informatik 201.5 31%
                Jantine Heller Informatik 348 54%
                Dongshu Wulf Informatik 111 17%
                Giuse Geppert Informatik 379.5 59%
                Secahattin Lauterbach Informatik 140 22%
                Manlio Arens Informatik 448 70%
                Notburga Theis Informatik 529 83%
                Nenat Wenk Informatik 0 0%
                Hans-Erdmann Schübel Informatik 320 50%
                Luula Hünsel Informationswissenschaften 175 27%
                Elmer Ketterer Informatik 46 7%
                Elspeth Küstler Physik 307.5 48%
                Imma Mühle Informatik 94.5 15%
                Tirthalli Graupner Informatik 204.5 32%
                Tuncoy Paschke Informatik 448 70%
                Nuray Hetzel Informatik 450 70%
                Meinolf Pohle Informatik 147 23%
                Ognjanovic Gebel Physik 527 82%
                Cassilda Wagner Informatik 580.5 91%
                Wern Freyer Informatik 4 1%
                Seyhan Flick Informatik 459 72%
                Koni Aigner Informatik 236.5 37%
                Candace Tewes Anderer 409.5 64%
                Doina Franzke Informatik 340 53%
                Mahmooo Arnold Physik 384 60%
                Tanno Mager Physik 434 68%
                Jai-Young Tewes Informatik 55 9%
                Gundi Wurster Informatik 403.5 63%
                Lelio Ochs Physik 542 85%
                Vaitsa Hüller Anderer 447.5 70%
                Saniye Ilg Informationswissenschaften 186 29%
                Toba Prill Informatik 43 7%
                Peter-Alexander Seibert Physik 332 52%
                Shereen Schün Informatik 204.5 32%
                Ekhard Mader Informationswissenschaften 111 17%
                Carel Heymann Informatik 177 28%
                Jeanny Gerth Informatik 212 33%
                Doro Bücker Informatik 153 24%
                Allard Prinz Informatik 383 60%
                Vijai Seemann Informatik 57 9%
                Hajo Philippi Informatik 15 2%
                Stiepo Kastl Mathematik 343.5 54%
                August Jahn Informatik 547.5 86%
                Dag Schmied Informatik 405.5 63%
                Duwald Carstens Anderer 151 24%
                Polina Reiners Informatik 427.5 67%
                Drage Weimann Informationswissenschaften 303.5 47%
                Arsic Reck Informationswissenschaften 62 10%
                Wilson Kilic Informatik 608.5 95%
                Faris Kühn Physik 192.5 30%
                Yun-Hee Steinert Informationswissenschaften 52 8%
                Petronilla Kullmann Informatik 125.5 20%
                Sona Herzig Informationswissenschaften 0 0%
                Koroush Burkhart Informatik 14 2%
                Bern Kautz Informatik 578.5 90%
                Virat Reusch Informationswissenschaften 21 3%
                Ingetraud Schanz Mathematik 386.5 60%
                Fahredin Seelig Informatik 426.5 67%
                Joachim Spindler Physik 245.5 38%
                Menachem Groükopf Informatik 0 0%
                Kujtim Wegener Informatik 84 13%
                Karl-Heribert Eckel Informatik 100 16%
                Anton-Johann Ney Informatik 0 0%
                Calogero Franken Informatik 13 2%
                Hany Kuck Anderer 0 0%
                Tom Yilmaz Informatik 356 56%
                Konidavis Griebel Informatik 309 48%
                Gertlinde Helbing Informatik 105 16%
                Berenike Sommerfeld Informatik 481 75%
                Aygün Janson Informatik 313 49%
                Toru Lambrecht Informatik 20 3%
                Conic Siefert Informationswissenschaften 36 6%
                Zeliha Meyer Informatik 394.5 62%
                Hans-Reinhardt Streit Informatik 630.5 99%
                Milanka Marschner Mathematik 560.5 88%
                Marie-Gabriele Heuser Informatik 0 0%
                Franciscus Hartl Informatik 84 13%
                Haysam Nehring Informatik 103 16%
                Alona Wacker Informatik 7 1%
                Tarek Ruge Informatik 0 0%
                Samantha Menge Informatik 325 51%
                Teodor Kaufhold Informatik 406 63%
                Ragmilla Pawlik Informatik 170 27%
                Chatchawan Lay Informatik 262.5 41%
                Finn Rott Informatik 594 93%
                Josko Schanz Informatik 476.5 74%
                Zeynel Kober Informatik 606.5 95%
                Irmtraut Bogner Informatik 0 0%
                Yuichiro Cramer Informationswissenschaften 40 6%
                Kidane Baltes Informatik 5 1%
                Engelbert Winkler Physik 358.5 56%
                Moira Knauer Informatik 1 0%
                Waitsa Wenger Informatik 40 6%
                Awais-ur-Rehman Fick Informatik 202.5 32%
                Emmanuele Heiü Informatik 73 11%
                Vrace Burkert Informatik 378.5 59%
                Denic Hermes Informatik 273 43%
                Irmentru Beil Anderer 63 10%
                Chao Heider Informatik 547.5 86%
                Lot Bormann Informatik 146 23%
                Eva-Rebecca Hass Informatik 0 0%
                Scott Christiansen Informatik 181.5 28%
                Yoram Schün Physik 212 33%
                Jahja Witte Informatik 87 14%
                Ahmadi Breier Physik 269 42%
                Borhan Szymanski Physik 314.5 49%
                Biswanath Kellermann Informatik 16 3%
                Bar Wolters Informatik 124 19%
                Alfiero Geisler Informatik 323.5 51%
                Tülek Ahlers Informatik 429 67%
                Zarko Meder Informatik 440 69%
                Vincencij Dürr Informatik 488 76%
                Rasetta Ostertag Informatik 95 15%
                Sulfekar Eich Informatik 99 15%
                Serafino Kopf Informatik 367 57%
                Gordon Stange Informatik 263 41%
                Gulistan George Informatik 16 3%
                Bayterin Tischler Informatik 317 50%
                Ludovic Gürtler Mathematik 601.5 94%
                Liebig Wenk Informationswissenschaften 0 0%
                Liddy Renner Physik 222.5 35%
                Gisbert Senger Physik 418 65%
                Selajdin Faber Informatik 399.5 62%
                Maira Heinecke Informatik 0 0%
                Mestan Dinkel Informatik 0 0%
                Milovad Lambert Informatik 172 27%
                Britta Busche Anderer 0 0%
                Elisab Maisch Informatik 394.5 62%
                Nives Kleber Informatik 416.5 65%
                Christodoulos Reinartz Informatik 0 0%
                Aloyz Traub Informatik 555.5 87%
                Maria-Marta Himmel Informatik 346 54%
                Sanita Marquardt Informatik 0 0%
                üzolp Grabow Physik 387 60%
                Ibro Drechsler Informatik 352 55%
                Nunziata Vollmer Informatik 408 64%
                Costin Schütz Informatik 305 48%
                Georg-Wilhelm Dressel Informatik 215.5 34%
                Oldag Groüe Mathematik 535 84%
                Kiet Knoth Mathematik 104 16%
                Grietje Kleber Informatik 243 38%
                Houssein-Mahmaud Alles Informatik 0 0%
                Eimantas Gaiser Anderer 482.5 75%
                Frewoin Tolksdorf Informatik 0 0%
                Gerd-Emil Bethke Informatik 354.5 55%
                Fahrit Sommer Informatik 98 15%
                Sobic Sieger Informatik 45 7%
                Bracko Jung Informatik 367 57%
                Alasdair Rogge Informatik 353 55%
                Melemez Bachmeier Informatik 502 78%
                Sefa Schreiter Informatik 0 0%
                Eljez Sahin Informatik 0 0%
                Jeanette Engelbrecht Informationswissenschaften 189 30%
                Dietrich Kiel Informatik 0 0%
                Vcina Spitzer Informationswissenschaften 243.5 38%
                Radoslav Christmann Informationswissenschaften 319.5 50%
                Ingo-Julius Kiefer Informatik 0 0%
                Innozenz Heyne Informatik 0 0%
                Eugen Kühner Informatik 112 18%
                Gotlind Schütt Mathematik 376 59%
                Arto Hock Informatik 0 0%
                Jerko Lehner Informatik 412.5 64%
                Eltje Schwan Informatik 458 72%
                Hazel Heck Informatik 173 27%
                Mali Voss Informatik 228 36%
                Acar Philipp Informatik 104 16%
                Judi Hoyer Informatik 292.5 46%
                Fayeg Schimpf Informatik 314 49%
                Ciciliz Schumacher Informatik 116 18%
                Godemund Otte Informatik 43 7%
                Bahram Brandenburg Informatik 396 62%
                Kemo Michler Informatik 144 23%
                Anneke Abels Informatik 0 0%
                Hadwig Kania Informatik 59 9%
                Pana Jordan Informatik 23 4%
                Sakir Nagel Informatik 327 51%
                Vieslava Brandt Informatik 40 6%
                Sjaan Hass Anderer 532.5 83%
                üner Pusch Informationswissenschaften 0 0%
                Zenan Wirtz Informatik 440.5 69%
                Labas Andersen Informatik 279 44%
                üzgir Bonn Informatik 246 38%
                Remzige Fox Informatik 229 36%
                Karl-Heinz Anders Informationswissenschaften 278 43%
                Madina Grosch Informatik 118 18%
                Stonimir Bühnke Informationswissenschaften 0 0%
                Sami Mahnke Informationswissenschaften 0 0%
                Giesberth Sowa Informatik 124 19%
                Alasani Marschner Informatik 425 66%
                Lucius Wolfrum Informatik 396 62%
                Hans-Peter Mattes Informatik 85 13%
                Pierr Platz Informatik 457 71%
                Papas Reichenbach Informationswissenschaften 224 35%
                Alaim Willms Informatik 0 0%
                Edmun Stemmer Informatik 343 54%
                Joselyne Hesse Informatik 293 46%
                Zulfiqar Schwabe Physik 315.5 49%
                Gasi Mainka Informatik 0 0%
                Kekik Hug Informatik 312 49%
                Caruso Rump Informationswissenschaften 0 0%
                Rodrigo Wohlfarth Informatik 481 75%
                Lascaris Heindl Informatik 424.5 66%
                Hansar Gebel Mathematik 442 69%
                Wernner Brose Informatik 329.5 51%
                Amir-Ahmad-Amiri Kunkel Anderer 116 18%
                Muhterem Andres Informatik 48 8%
                Traude Lang Informationswissenschaften 0 0%
                Erwi Baron Mathematik 162 25%
                Ulusoy Kaul Informationswissenschaften 270 42%
                Zisis Bucher Informatik 0 0%
                Manuela Weingürtner Informationswissenschaften 57 9%
                Mary Greiner Informatik 8 1%
                Marielle Petermann Informatik 536.5 84%
                Mogi Stüber Informatik 194 30%
                Ismail-Oglou Kliem Informatik 100 16%
                Ha-Jo Glas Informationswissenschaften 315 49%
                Ejder Pfister Informatik 299.5 47%
                Hilu Nowak Informatik 326 51%
                Djuradj Kellermann Informatik 72 11%
                Antic Weidinger Anderer 248 39%
                Arikan Wanke Informatik 300 47%
                Erk Kerner Informatik 267.5 42%
                Bars Spitz Mathematik 301.5 47%
                Liebig Wellmann Informatik 51 8%
                Dusanka Wege Informatik 86 13%
                Kadir Hoffmann Informatik 166.5 26%
                Firdes Hüft Informatik 0 0%
                Süheyl Kropp Informatik 355 55%
                Maxine Bauch Informatik 0 0%
                Delice Ostendorf Informatik 109 17%
                Edgar Kipp Informatik 60 9%
                Ulfried Lüdemann Informationswissenschaften 504.5 79%
                Seyide Wirth Informatik 0 0%
                Irma-Maria Bangert Informationswissenschaften 10 2%
                Radoslaw Bergemann Physik 376 59%
                Kuddusi Küstner Informatik 0 0%
                Ritza Clauüen Physik 0 0%
                Federiko Rust Informatik 600.5 94%
                Lykke Glüser Informationswissenschaften 0 0%
                Tayser Stolte Physik 520 81%
                Leu Fix Informatik 4 1%
                Jashar Kellner Informatik 42 7%
                Houssein-Mahmaud Frahm Informationswissenschaften 65 10%
                Jivko Heinzmann Informatik 227.5 36%
                Slawomir Abel Informationswissenschaften 0 0%
                Vrana Graupner Informationswissenschaften 28 4%
                Derick Paulus Informatik 346 54%
                Nikolo Becher Informatik 0 0%
                Fengzhi Sigl Informatik 311 49%
                Laura Küster Informatik 89 14%
                Nasip Kupfer Informatik 64 10%
                Ezatollah Klar Informationswissenschaften 359 56%
                Ruhland Melcher Informatik 350 55%
                Jirka Kremser Informatik 50 8%
                Leylan Herdt Informatik 0 0%
                Regine Widmann Informatik 113 18%
                Agali Rosenberger Informationswissenschaften 67.5 11%
                Waltrud Adrian Informatik 0 0%
                Mercinek Stoll Mathematik 332.5 52%
                Stephanos Edler Informatik 0 0%
                Iftade Rutz Informatik 448 70%
                Erguin Schlick Informatik 22 3%
                Rojec Emmerich Informatik 265.5 41%
                Kastor Heinke Informationswissenschaften 0 0%
                Jeannette Lühr Mathematik 0 0%
                Pandora Lex Physik 60 9%
                Irmentraud-Ursula Deppe Mathematik 0 0%
                Gabor Salewski Physik 0 0%
                Arsla Reinke Informatik 0 0%
                Youngdal Bracht Physik 0 0%
                Prend Engelhard Informatik 108.5 17%
                Koen Sebastian Informatik 0 0%
                Elf Flaig Mathematik 0 0%
                Gallier Matthes Informationswissenschaften 0 0%
                Julius Salewski Physik 0 0%
                Nektaria Spindler Anderer 0 0%
                Rizos Herber Informationswissenschaften 461 72%
                Kazim Heiden Informatik 96 15%
                Jan-Steffen Schlüter Informationswissenschaften 0 0%
                Ryszavol Wendler Informatik 37 6%
                Joschi Lederer Informatik 0 0%
                Quennadi Saller Informationswissenschaften 0 0%
                Paraskevas Kugler Informatik 0 0%
                Mikajle Spiekermann Informatik 0 0%
                Halilibrahim Sieg Informatik 71 11%
                Nena Lieder Informatik 197 31%
                Sudiye Hilbert Anderer 87.5 14%
                Berek Wenger Informationswissenschaften 0 0%
                Morawe Gutzeit Informationswissenschaften 0 0%
                Frandisek Behrend Informatik 366.5 57%
                Sven-Olaf Reese Informatik 0 0%
                Nouma Lindner Informationswissenschaften 24 4%
                Ringo Raab Informatik 0 0%
                Rainer-Michael Wille Informatik 0 0%
                Mikolaj Wimmer Informatik 292 46%
                Friedholde-Ina Georgi Physik 0 0%
                Gerfriede Kühl Informatik 47 7%
                Euge Schultes Informatik 302.5 47%
                Kilic Kothe Informatik 0 0%
                Candan Ehret Informatik 0 0%
                Luigio Goller Informatik 0 0%
                Behzat Raabe Informationswissenschaften 0 0%
                Keto Bruckner Physik 63 10%
                Yargi Schütze Informatik 74 12%
                Blagoja Bohn Informatik 0 0%
                Budis Merz Anderer 25 4%
                Birsel Appelt Informatik 34 5%
                Orphü Figge Physik 119 19%
                Rakesh Wirtz Informatik 320 50%
                Behice Wiedemann Physik 0 0%
                Türckan Mühlberger Informationswissenschaften 0 0%
                Stipü Carstensen Informatik 136 21%
                Arnolf Reich Informatik 0 0%
                Isol Titze Informationswissenschaften 0 0%
                Ingrassia Clasen Informatik 0 0%
                Dorotheü Schweizer Informatik 0 0%
                Vigil Burger Physik 0 0%
                Nadschibeh Fischbach Informatik 93 15%
                Bardo Breidenbach Informatik 60 9%
                Ousmanou Bracht Informationswissenschaften 0 0%
                Gkiukz Kummer Informatik 304 48%
                Ildebrando Rose Informationswissenschaften 0 0%
                Liselotte Pelzer Informatik 316 49%
                Nilos Straub Informatik 0 0%
                Badema Schünberger Informationswissenschaften 0 0%
                Mazmiye Schaaf Anderer 491 77%
                Bosco Mann Informatik 37 6%
                Emrullah Szymanski Informatik 0 0%
                Ivar-Henrik Mey Informatik 263 41%
                Gertra Bucher Physik 246.5 39%
                Wichote Rolle Informationswissenschaften 78 12%
                Annalies Fuchs Informatik 369.5 58%
                Azad Stief Informatik 0 0%
                Nicolaus Adam Mathematik 262 41%
                Ling-Yi Welzel Informatik 290 45%
                Cin Hildebrandt Informatik 76 12%
                Philippas Stobbe Informatik 38 6%
                Stefanija Sperl Informatik 55 9%
                Genco Schuhmann Informatik 0 0%
                Bettin Schulze Informatik 74 12%
                Beth Eggert Informatik 161.5 25%
                Radenko Fritz Mathematik 300 47%
                Tabo Philipps Informatik 0 0%
                Matija Gottschalk Informatik 0 0%
                """.lines().toList();
    }
}
