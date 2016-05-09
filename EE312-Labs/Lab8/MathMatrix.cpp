/*
AL HIRANI
AH45675
16220
EE 312- Assignment 8
*/
#include "MathMatrix.h"
using namespace std;

MathMatrix::MathMatrix()
{
	mat.resize(1);
	mat[0].resize(1);
	mat[0][0] = 0;
}

MathMatrix::MathMatrix(int numRows, int numCols, int** matrix)
{
	mat.resize(numRows);
	for (int i = 0; i < numRows; i++)
		mat[i].resize(numCols);
	for (int rows = 0; rows < numRows; rows++)
		for (int cols = 0; cols < numCols; cols++)
			mat[rows][cols] = matrix[rows][cols];
}

MathMatrix::MathMatrix(const vector<vector<int>>& matrix)
{
	vector<vector<int>>::const_iterator p;
	vector<int>::const_iterator q;
	int rowcount = 0;
	int colcount = 0;
	for (p = matrix.begin(); p < matrix.end(); p++)
		rowcount++;
	for (q = matrix[0].begin(); q < matrix[0].end(); q++)
			colcount++;
	mat = matrix;
}

MathMatrix::MathMatrix(int numRows, int numCols, int initialValue)
{
	mat.resize(numRows);
	for (int i = 0; i < numRows; i++)
		mat[i].resize(numCols);
	for (int i = 0; i < numRows; i++)
		for (int j = 0; j < numCols; j++)
		{
			mat[i][j] = initialValue;
		}
}

MathMatrix::~MathMatrix()
{
	
}

void MathMatrix::changeElement(int row, int col, int newValue)
{
	mat[row][col] = newValue;
	return;
}
int MathMatrix::numRows() const
{
	vector<vector<int>>::const_iterator p;
	int counter = 0;
	for (p = mat.begin(); p < mat.end(); p++)
		counter++;
	return counter;
}
int MathMatrix::numCols() const
{
	vector<vector<int>>::const_iterator p;
	vector<int>::const_iterator q;
	p = mat.begin();
	int counter = 0;
	for (q = (*p).begin(); q < (*p).end(); q++)
		counter++;
	return counter;

}
int MathMatrix::getVal(int row, int col)
{
	return mat[row][col];
}
MathMatrix MathMatrix::add(const MathMatrix& rightHandSide)
{
	MathMatrix result;
	result.mat.resize(rightHandSide.numRows());
	for (int morgansbutt = 0; morgansbutt < rightHandSide.numRows(); morgansbutt++)
		result.mat[morgansbutt].resize(rightHandSide.numCols());
	for (int i = 0; i < rightHandSide.numRows(); i++)
		for (int j = 0; j < rightHandSide.numCols(); j++)
			result.mat[i][j] = this->mat[i][j] + rightHandSide.mat[i][j];
	return result;
}
MathMatrix MathMatrix::subtract(const MathMatrix& rightHandSide)
{
	MathMatrix result;
	result.mat.resize(rightHandSide.numRows());
	for (int morgansbutt = 0; morgansbutt < rightHandSide.numRows(); morgansbutt++)
		result.mat[morgansbutt].resize(rightHandSide.numCols());
	for (int i = 0; i < rightHandSide.numRows(); i++)
		for (int j = 0; j < rightHandSide.numCols(); j++)
			result.mat[i][j] = this->mat[i][j] - rightHandSide.mat[i][j];
	return result;
}
MathMatrix MathMatrix::multiply(const MathMatrix& rightHandSide)
{
	MathMatrix result;
	result.mat.resize(numRows());
	for (int i = 0; i < numRows(); i++)
		result.mat[i].resize(rightHandSide.numCols());
	for (int r = 0; r < this->numRows(); r++)
		for (int c = 0; c < rightHandSide.numCols(); c++)
			for (int i = 0; i < this->numCols(); c++)
				result.mat[r][c] += this->mat[r][i] * rightHandSide.mat[i][c];
	return result;
}

bool MathMatrix::equals(const MathMatrix& rightHandSide)
{
	if (this->numRows() != rightHandSide.numRows() || this->numCols() != rightHandSide.numCols())
		return false;
	for (int i = 0; i < rightHandSide.numRows(); i++)
		for (int j = 0; j < rightHandSide.numCols(); j++)
			if (this->mat[i][j] != rightHandSide.mat[i][j])
				return false;
	return true;
}

MathMatrix MathMatrix:: operator+(const MathMatrix& rightHandSide)
{
	MathMatrix result;
	result = add(rightHandSide);
	return result;
}

MathMatrix MathMatrix:: operator-(const MathMatrix& rightHandSide)
{
	MathMatrix result;
	result = subtract(rightHandSide);
	return result;
}
MathMatrix MathMatrix:: operator*(const MathMatrix& rightHandSide)
{
	MathMatrix result;
	result = multiply(rightHandSide);
	return result;
}

bool MathMatrix:: operator==(const MathMatrix& rightHandSide)
{
	return equals(rightHandSide);
}

MathMatrix MathMatrix::scale(int factor)
{
	MathMatrix result;
	result.mat.resize(numRows());
	for (int f = 0; f < numRows(); f++)
		result.mat[f].resize(numCols());
	for (int i = 0; i < numRows(); i++)
		for (int j = 0; j < numCols(); j++)
			result.mat[i][j] = this->mat[i][j] * factor;
	return result;
}
MathMatrix MathMatrix::getTranspose()
{
	MathMatrix result;
	result.mat.resize(numRows());
	for (int f = 0; f < numRows(); f++)
		result.mat[f].resize(numCols());
	for (int i = 0; i < numRows(); i++)
		for (int j = 0; j < numCols(); j++)
			result.mat[i][j] = this->mat[j][i];
	return result;
}