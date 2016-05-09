/*
AL HIRANI
AH45675++
16220
EE 312- Assignment 8
*/
#include "SquareMatrix.h"
using namespace std;

SquareMatrix::SquareMatrix()
{
	d = 0;
	mat.resize(1);
	mat[0].resize(1);
	mat[0][0] = 0;
}

SquareMatrix::SquareMatrix(int size, int initialValue)
{
	d = 0;
	mat.resize(size);
	for (int i = 0; i < size; i++)
		mat[i].resize(size);
	for (int i = 0; i < size; i++)
		for (int j = 0; j < size; j++)
			mat[i][j] = initialValue;
}

SquareMatrix::~SquareMatrix()
{
	//NOTHING ALLOCATED ONTO THE HEAP THEREFORE NO CODE FOR DESTRUCTOR
}

SquareMatrix SquareMatrix::createIdentityMatrix(int size)
{
	SquareMatrix result;
	mat.resize(size);
	for (int f = 0; f < size; f++)
		mat[f].resize(size);
	for (int i = 0; i < size; i++)
		for (int j = 0; j < size; j++)
		{
			if (i == j)
				result.mat[i][j] = 1;
			else
				result.mat[i][j] = 0;
		}
	return result;
}

int SquareMatrix::getDeterminant()
{
	int det = 0;
	if (numCols() == 2)
	{
		det = getVal(0, 0)*getVal(1, 1) - getVal(0, 1)*getVal(1, 0);
		return det;
	}
	if (numCols() == 3)
	{
		det = getVal(0, 0)*getVal(1, 1)*getVal(2, 2);
		det += getVal(0, 1)*getVal(1, 2)*getVal(2, 0);
		det += getVal(0, 2)*getVal(1, 0)*getVal(2, 1);
		det -= getVal(2, 0)*getVal(1, 1)*getVal(0, 2);
		det -= getVal(2, 1)*getVal(1, 2)*getVal(0, 0);
		det -= getVal(2, 2)*getVal(1, 0)*getVal(0, 1);
		return det;
	}
	return 0;
	/*if (this->d != 0)
	{
		return d;
	}
	if (this->n == 0)
		this->n = numRows();
	int c, subi, i, j, subj;
	SquareMatrix submat;

	if (this->n == 2)
	{
		return((mat[0][0] * mat[1][1]) - (mat[1][0] * mat[0][1]));
	}
	else
	{
		submat.mat.resize(10);
		for (int a = 0; a < this->mat.size(); a++)
			submat.mat[a].resize(10);
		for (c = 0; c < this->n; c++)
		{
			subi = 0;
			for (i = 1; i < this->n; i++)
			{
				subj = 0;
				for (j = 0; j < this->n; j++)
				{
					if (j == c)
					{
						continue;
					}
					submat.mat[subi][subj] = mat[i][j];
					subj++;
				}
				subi++;
			}
			submat.n = this->n - 1;
			d = d + ((int)pow(-1, c) * mat[0][c] * submat.getDeterminant()); //det(n - 1, submat));
		}
	}
	return d;
	*/
}

bool SquareMatrix::isSymmetric()
{
	return this->equals(this->getTranspose());
}