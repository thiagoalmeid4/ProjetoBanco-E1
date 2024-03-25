package br.com.banco.view;
//
//import java.time.LocalDate;
//import java.time.Period;
//import java.time.format.DateTimeFormatter;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Input {
//
//    private static Scanner scanner;
//    private static DateTimeFormatter dateFormatter;
//
//    public static String getNome() {
//        scanner = new Scanner(System.in);
//        String nome = scanner.next().trim();
//
//        if (nome.isEmpty() || !nome.matches("[a-zA-ZÀ-ú]+(\\s[a-zA-ZÀ-ú]+)*")) {
//            System.out.println("Nome inválido. Tente novamente.");
//            return getNome();
//        }
//
//        return nome.toUpperCase();
//    }
//
//    public static String getEmail() {
//        scanner = new Scanner(System.in);
//        String email = scanner.next().trim();
//
//        if (!validarEmail(email)) {
//            System.out.println("E-mail inválido. Tente novamente.");
//            return getEmail(); 
//        }
//
//        return email;
//    }
//
//    public static LocalDate getData() {
//        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        scanner = new Scanner(System.in);
//        String dataString = scanner.next().trim();
//        LocalDate hoje = LocalDate.now();
//
//        try {
//            LocalDate data = LocalDate.parse(dataString, dateFormatter);
//            Period periodo = Period.between(data, hoje);
//            int idade = periodo.getYears();
//            
//            if (idade > 90) {
//    			System.out.println("Data de nascimento inválida");
//    			return getData();
//    		}
//            
//    		if (idade < 18) {
//    			System.out.println("O usuário deve ter 18 anos ou mais");
//    			return getData();
//    		}
//            
//            return data;
//        } catch (Exception e) {
//            System.out.println("Data inválida. Tente novamente.");
//            return getData();
//        }
//    }
//
//    public static String getCpf() {
//        scanner = new Scanner(System.in);
//        String cpf = scanner.next().trim();
//
//        if (!validarCpf(cpf)) {
//            System.out.println("CPF inválido. Tente novamente.");
//            return getCpf(); 
//        }
//
//        return cpf;
//    }
//
//    public static String getValorMonetario() {
//        scanner = new Scanner(System.in);
//        String valor = scanner.next().trim();
//
//        if (valor.isEmpty() || valor.equals("0")) {
//            System.out.println("Valor inválido. Tente novamente.");
//            return getValorMonetario();
//        }
//
//        valor = valor.replace(",", ".");
//
//        return valor;
//    }
//
//    public static String getSenha() {
//        scanner = new Scanner(System.in);
//        String senha = scanner.next().trim();
//
//        if (senha.isEmpty() || senha.length() < 6) {
//            System.out.println("Senha inválida. Tente novamente.");
//            return getSenha();
//        }
//
//        return senha;
//    }
//
//    private static boolean validarEmail(String email) {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//
//        return email.matches(emailRegex);
//    }
//
//    private static boolean validarCpf(String CPF) {
//        if (CPF.equals("00000000000") ||
//            CPF.equals("11111111111") ||
//            CPF.equals("22222222222") || CPF.equals("33333333333") ||
//            CPF.equals("44444444444") || CPF.equals("55555555555") ||
//            CPF.equals("66666666666") || CPF.equals("77777777777") ||
//            CPF.equals("88888888888") || CPF.equals("99999999999") ||
//            (CPF.length() != 11))
//            return(false);
//
//        char dig10, dig11;
//        int sm, i, r, num, peso;
//
//        try {
//            sm = 0;
//            peso = 10;
//            for (i=0; i<9; i++) {
//            num = (int)(CPF.charAt(i) - 48);
//            sm = sm + (num * peso);
//            peso = peso - 1;
//            }
//
//            r = 11 - (sm % 11);
//            if ((r == 10) || (r == 11))
//                dig10 = '0';
//            else dig10 = (char)(r + 48);
//
//            sm = 0;
//            peso = 11;
//            for(i=0; i<10; i++) {
//            num = (int)(CPF.charAt(i) - 48);
//            sm = sm + (num * peso);
//            peso = peso - 1;
//            }
//
//            r = 11 - (sm % 11);
//            if ((r == 10) || (r == 11))
//                 dig11 = '0';
//            else dig11 = (char)(r + 48);
//
//            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
//                 return(true);
//            else return(false);
//                } catch (InputMismatchException erro) {
//                return(false);
//            }
//    }
//
//    public void closeScanner() {
//        scanner.close();
//    }
//}
