#include <vector>
using namespace std;

class MathMatrix
{
protected:
	/* add any protected member variables you require */
	vector<vector<int>> mat;
private:

public:

	/*
		Default constructor initializes a 1x1 matrix
		with an initial value of 0
	*/
	MathMatrix();

	/*
		Initializes a MathMatrix from int** mat
		which has numRows rows and numCols columns.
	*/
	MathMatrix(int numRows, int numCols, int** arr);
    
    /*
     Initializes a MathMatrix from an existing matrix
     */
    MathMatrix(const vector<vector<int>>& arr);

	/*
		Initializes a MathMatrix of numRows rows and
		numCols columns with an initial value of
		initialValue in each position
	*/
	MathMatrix(int numRows, int numCols, int initialValue);

	/*
		Destructor - delete all allocated member variables
	*/
	~MathMatrix();

	/*
		Changes the value in position row, col to newValue
	*/
	void changeElement(int row, int col, int newValue);
	
	/*
		Returns the number of rows in the MathMatrix
	*/
	int numRows() const;

	/*
		Returns the number of columns in the MathMatrix
	*/
	int numCols() const;

	/*
		Returns the value in position row, col
	*/
	int getVal(int row, int col);

	/*
		Returns the result of matrix addition as a new MathMatrix
	*/
	MathMatrix add(const MathMatrix& rightHandSide);

	/*
		Returns the result of matrix subtraction as a new MathMatrix
	*/
	MathMatrix subtract(const MathMatrix& rightHandSide);

	/*
		Returns the result of matrix multiplication as a new MathMatrix
	*/
	MathMatrix multiply(const MathMatrix& rightHandSide);

	/*
		Returns true if two MathMatricies have the same int in every
		position in the matrix. Note: matricies are not necessarily 
        equal in size.
	*/
	bool equals(const MathMatrix& rightHandSide);

	/*
		Overloaded + operator. Performs matrix addition.
	*/
	MathMatrix operator+(const MathMatrix& rightHandSide);

	/*
		Overloaded - operator. Performs matrix subtraction.
	*/
	MathMatrix operator-(const MathMatrix& rightHandSide);

	/*
		Overloaded * operator. Performs matrix multiplication
	*/
	MathMatrix operator*(const MathMatrix& rightHandSide);

	/*
		Overloaded == operator. Returns true if two MathMatricies
		are equal by the definition given above.
	*/
	bool operator==(const MathMatrix& rightHandSide);

	/*
		Multiplies each element in the matrix by a scaler and
		returns a new MathMatrix.
	*/
	MathMatrix scale(int factor);

	/*
		Returns the transpose of a MathMatrix by making each
		row a column and each column a row.
	*/
	MathMatrix getTranspose();

};