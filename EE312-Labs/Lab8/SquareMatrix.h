#include "MathMatrix.h"
using namespace std;

class SquareMatrix : public MathMatrix {
private:
	int d;
	int n;
	/* 
	   add any private member variables you require 
	   that were not inherited from the MathMatrix
	   class
	*/
public:

	/*
		Default constructor initializes a 1x1 matrix
		with an initial value of 0
	*/
	SquareMatrix();

	/*
		Destructor - delete all allocated member variables
	*/
	~SquareMatrix();

	/*
		Constructs a size x size SquareMatrix with an 
		initial value of initialValue.
	*/
	SquareMatrix(int size, int initialValue);

	/*
		Returns a new SquareMatrix that is of dimensions
		size x size with 1's along the diagonal and 0
		in all other positions.
	*/
	SquareMatrix createIdentityMatrix(int size);

	/*
		Returns the determinant of the matrix.
	*/
	int getDeterminant();

	/*
		Returns true if SquareMatrix is equal to the
		transpose of itself.
	*/
	bool isSymmetric();
};