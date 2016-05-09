typedef struct Node {
	double coeff;
	int degree;
	Node* next;
} Node;

// function where the interactive menu should be implemented
void runProgram();

class Poly {
private:
	Node *base;
	Poly* multList;
	Poly* temp;
	Node* newNode;
	// add private members as needed
public:
	Poly(char* str);
	~Poly();
	Poly* add(Poly& otherPoly);
	Poly* multiply(Poly& otherPoly);
	double eval(double x);
	void print();
	bool equals(Poly& otherPoly);
	Poly* operator+(Poly& otherPoly);
	Poly* operator*(Poly& otherPoly);
	double operator()(double x);
	bool operator==(Poly& otherPoly);
	// add public members if needed
};