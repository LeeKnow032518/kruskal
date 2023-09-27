import java.util.*;
import java.util.Scanner;

// хранение ребер
class Edges{
    int from, to, dist;

    public Edges(int f, int t, int d)
    {
        this.from = f;
        this.to = t;
        this.dist = d;
    }
}

// хранение ссылок на представителя
class Links{
    Map<Integer, Integer> represent = new HashMap<>(); // представители каждого элемента
    Map<Integer, Integer> ranks = new HashMap<>(); // ранги элементов

    // функция создания одноэлементных множеств
    public void create(int n){
        for(int i=0;i<n;i++)
        {
            represent.put(i, i);
        }
    }

    // присвоение всем вершинам 0 ранга
    public void range(int n){
        for(int i=0;i<n;i++){
            ranks.put(i, 0);
        }
    }

    private int find(int a){
        if(represent.get(a) == a){
            return a;
        }
        else{
            represent.put(a, find(represent.get(a))); // ищем представителя представителя а
            return represent.get(a);
        }
    }

    private void union(int a, int b){
        int x = find(a);
        int y = find(b);

        // к дереву с бОльшим рангом присоединяем дерево с меньшим рангом
        if(ranks.get(x) >= ranks.get(y)){
            represent.put(y, x);
            if(ranks.get(x) == ranks.get(y)){ // если ранги совпали, то увеличиваем ранг х на 1
                ranks.put(x, ranks.get(x)+1);
            }
        }
        else{
            represent.put(x, y);
        }
    }

    public static String KruskalAlg(List<Edges> edges, int n, int m){

        Links ds = new Links();
        ds.create(n);
        ds.range(n);

        int index = 0; // счетчик ВСЕХ ребер
        int weight = 0; // суммарный вес ребер MST
        int count =0; // количество взятых ребер

        Collections.sort(edges, Comparator.comparingInt(e -> e.dist));

        while(index < m) {

            Edges next_one = edges.get(index++);

            int x = ds.find(next_one.from);
            int y = ds.find(next_one.to);

            if (x != y) {
                count++;
                ds.union(x, y);
                weight += next_one.dist; // прибавляем вес
            }
            if(count == n-1)
            {
                break;
            }
        }
git 
        String result;
        result = Integer.toString(n-count) + " " + Integer.toString(weight);

        return result;
    }
}

class Main{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        int N = scanner.nextInt(); // количество вершин
        int M = scanner.nextInt(); // количество ребер

        List<Edges> edges = new ArrayList<>();

        for(int i=0;i<M;i++) // считываем ребра
        {
            numbers = scanner.nextLine();
            Edges new_edge = new Edges(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            edges.add(new_edge);
        }

        String length = Links.KruskalAlg(edges, N, M);
        System.out.println(length);
    }
}
