# Version-Control-System
Homework for the Object Oriented Programming course @ ACS, UPB 2018

# Algorithm

 - am realizat o clasa `Commit` cu 3 campuri pentru a retine caracteristicile 
acestei comenzi. Un camp retine id-ul commit-ului, un altul retine clona 
filesystem-ului curent, iar ultimul retine operatia/operatiile aplicata de commit.
 - am creat o clasa `Branch` in care am retinut caracteristicile unei comenzi de
 acest tip, numele branch-ului si o lista cu commit-urile pe care le detine.
 - pentru fiecare operatie, am realizat cate o clasa care executa comanda data
 si schimbarile pe care le realizeaza, dupa cum urmeaza:

 1) Clasa `CommitOperation` - verifica daca lista cu operatiile din Staging este
 goala, altfel adauga un obiect de tip `Commit` in lista branch-ului curent dat
 de `nameHead`. Fiecare obiect de tip commit retine o clona a filesystem-ului
 curent. Dupa fiecare commit adaugat, se goleste lista care retine operatiile
 in `Staging`.

 2) Clasa `BranchOperation` verifica initial, in functia execute, daca mai exista
 alt branch cu acelasi nume, in caz contrar cream un obiect de tip branch si
 copiem toate obiectele te tip commit din `nameHead` in lista noului branch.
In final, adaugam branch-ul in lista cu branch-uri.

 3) Clasa `CheckoutOperation` realizeaza operatia, in functia execute, dupa 2
 cazuri, de tip branch sau de tip commit. Initial, verifica daca lista cu 
comenzile din staging este goala. Daca comanda este pentru commit-uri, sterg
 commit-urile care urmeaza dupa commit-ul cu id-ul dat de comanda, dupa care
 setez `activeSnapshot` la la versiunea data de ultimul commit. Daca comanda este
 de tip branch, verific daca exista branch-ul cu numele dat, in caz afirmativ,
 setez pointerul `nameHead` la branch (salveaza referinta branch-ului respectiv). 

 4) Clasa `InvalidVcsOperation` returneaza eroarea dupa ce verifica daca comanda
 este valida in metoda execute.

 5) Clasa `LogOperation` realizeaza, in metoda execute, afisarea caracteristicilor
 unei comenzi de tip commit (id-ul si mesajul comenzii). Se ia branchul curent
 cu ajutorul pointerului `nameHead` si este parcursa lista sa de commit-uri.

 6) Clasa `RollbackOperation` realizeaza in metoda execute, operatia de golire a
 staging-ului curent si de aducere a snapshot-ului de filesystem la versiunea
 data de ultimul commit.

 7) Clasa `StatusOperation` afiseaza numele branch-ului curent și modificarile care
 sunt in staging cu ajutorul metodei `statusCommand()` din clasa `Vcs` care
 efectueaza parsarea numelor comenzilor din staging.
 
 In clasa `Vcs` am declarat si intantiat urmatoarele variabile: lista
`stagingOperationList` de tip `AbstractOperation`, lista `statusOperation` de tip
`String`, lista `branchHead` de tip `Branch` si variablia statica `nameHead` de tip 
`Branch` in efectuarea comenzilor date. Fiecare camp declarat si instantiat are 
get-eri, set-eri si alte metode specifice, dupa caz, pentru a interactiona cu
operatiile de tip vcs.
