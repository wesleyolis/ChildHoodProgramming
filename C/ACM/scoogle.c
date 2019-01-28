#include <stdio.h>
#include <stdlib.h>

struct node
{
	int cNode;
	int tNode;
	int delay;
	struct node *next;
};

struct node *start, * curr;

void addlist(int cNode, int tNode, int delay)
{
	if(start == NULL)
	{
		start = (struct node*) malloc(sizeof(struct node));
		start->cNode = cNode;
		start->tNode = tNode;
		start->delay = delay;
		curr = start;
	}
	else
	{
		curr->next = (struct node*) malloc(sizeof(struct node));
		curr = curr->next;
		curr ->cNode = cNode;
		curr ->tNode = tNode;
		curr ->delay = delay;
	}	
}

void calpaths()
{
	int last - 1;
	curr = start;
	while(curr != null)
	{
		if(curr->cNode
		curr = curr->next;
	}
}

int main()
{
	start = NULL;

	int nodes,mNodes, elements,mElements,tNode,delay,last = -1;
	printf("number nodes\n");
	scanf("%d", &mNodes);
	for(nodes = 0; nodes < mNodes; nodes++)
	{
	printf("number of elements\n");
	scanf("%d",&mElements);
			for(elements = 0; elements < mElements;elements++)
			{
			printf("element\n");
			
				scanf("%d%d",&tNode,&delay);
				while(last < (tNode -1)))
				{
					addlist(nodes,tNode,-1);
				}
				addlist(nodes,tNode,delay);
			}
	} 
	if(mNodes !=0)
	{
	curr->next = NULL;
	}
	
	calpaths();
	
	return 0;
}

