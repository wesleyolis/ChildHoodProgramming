



#ifndef _i2c_header
#define _i2c_header
//initialize varibles here

/*Buffer for i2c*/
/*
typedef struct{
	unsigned char comp_addr;
	buffer packets;	
}buf_dev_pack;

typedef struct{
	buf_dev_pack *bf;
	char b_s;	//in size
	char b_i;	//in pointer
	char b_o;	//out put pointer
	char b_c; // buffer counter
}buf_dev;
*/

typedef struct{
	unsigned char comp_addr;
	buffer packets;	
}buf_dev_pack;

typedef struct{
	buf_dev_pack *bf;
	char b_s;	//in size
	char b_i;	//in pointer
	char b_o;	//out put pointer
	char b_c; // buffer counter
}buf_dev;

typedef struct{
	buf_dev_pack *bf;
	buf_int b_s;	//in size
	buf_int b_i;	//in pointer
	buf_int b_o;	//out put pointer
	buf_int b_c; // buffer counter
	
	//same for the managing of the lower buffer storage space for the packets.
	// Because we dont have dynamic memory, a single array buffer is declare
	// on initialsation, then this memory space will used to dynamic resever
	// memory space for smaller buffers that lengths may vary.
	buffer bf_s;

/*	buf_char *bf_p; 
	char b_p_s;
	char b_p_i;
	char b_p_o;
	char b_p_c;*/
}buf_dev2;
#endif


void i2c_init(void);















#include "buffer.h"
#include "i2c.h"
#include "sfr_r827.h"	



/*Buffers for i2c*/
/*
char buf_dev_init(buf_dev *buf, buf_dev_pack *bf_p, buf_int s,buffer bf_s_p,buf_int bf_s_s)
{
	if(size<255)
	{
		buf->bf = bf_p;
		buf->b_s = size;
		buf->b_i=0;
		buf->b_o=0;
		buf->b_c=0;	
		//initialize the sub buffer for dynamic memory.
		buf_init(buf->bf_s,bf_s_p,bf_s_s);
		return 1;
	}
	else
	{
		return 0;	
	}
}

char buf_dev_put(buf_dev *buf, buf_dev_pack c)
{
	if(buf->b_c < buf->b_s)
	{
		buf->bf[buf->b_i++] = c;
		if(buf->b_i==buf->b_s)
		buf->b_i=0;
		buf->b_c++;
		return 1;
	}
	else
	return 0;
}

buf_dev_pack buf_dev_get(buf_dev *buf)
{
	buf_dev_pack c;
	if(buf->b_c!=0)
	{
		c = buf->bf[buf->b_o++];
		if(buf->b_o >= buf->b_s)
		buf->b_o=0;
		buf->b_c--;
		return c;
	}	
	else
	return 0;
	
}  
*/
/************************************************************************************
Name:		 i2c_init  
Parameters:  None
Returns:     None
Description: initializes the i^2c bus
************************************************************************************/

void i2c_init(void)
{ 	
	sar=0x10;			//transmit/recirve
	iicsel=1;			// I2C bus function select
	stop_icsr = 0;		//Disables stop condition detection interrupt/Stop condition detection flag
	ice_iccr1=1;		//This module is enabled for transfer operations/IIC bus interface enable bit
	iccr1=(iccr1 & 0xf0)|(0x0D);
}

/************************************************************************************
Name:		 i2c_transmit  
Parameters:  buf_dev_pack, the data, packets to transmit to the deveice
Returns:     None
Description: communicates over the i2c with other devices
************************************************************************************/

void i2c_communicate (buf_dev_pack packet)
{	
	//	stop_icsr=0;
	// 	iicsel_pmr=1;

	// (1)	Judge the state of the SCL and SDA lines
	while (bbsy_iccr2 != 0);	// p292 Bus busy bit	
	
	// (2)	Set to Master Tansmit Mode
	iccr1=(iccr1&0xCF)|(0x30);		// p292 I2C bus Control Register 1/ sets trs,mst bits = 1
	
	// (3)	Generate Start condition
	iccr2 = (iccr2 & 0x3F)|(0x80);	// p293  I2C bus Control Register 2 initial conditions / scp=0 bbsy=1
	
	// (4)	Set the transmit data of the 1st byte
	//		The First bit in i2c communication is usually the dev address and read and write state. 
	icdrt = packet->comp_addr;		// p297
	
	// (5)	Wait for one byte to be transmitted
	while (tend_icsr != 1);		/* Transmit end if = 0 */
	
	//Check which mode write or read and follow the required insructions
	// A write is 0 = LSB and read 1=LSB 
	if((packet->comp_addr&0b00000001)==&0b00000000)
	{



		packet->packets
		// (6) Judge the ACKBR bit from the spesified slave device
		if (ackbr_icier == 0)		// p295 recieve Acknowledge bit from receive device in transmit mode is set to 0.
		{		
			//loop this writting out the packets	
			icdrt=DATA;
			while (tend_icsr != 1);		//Transmit data not empty / Wait until the ICRDT register is empty	
			//end the loops this
		}
			// (11)	Set the Tend bit to 0
			tend_icsr=0;
		
			// (12) Set the stop bit to 0
			stop_icsr=0;	
	
			// (13) Generate the stop condition
			iccr2 = (iccr2&0b00111111)|(0b00000000);
		
			// (14)	 Wait until the sop condition is generated
			while(stop_icsr!=1);		
		
			// (15)	 Select slave receive mode (trs=0, mst=0)
			iccr1 = (iccr1&0b11001111)|(0b00000000);
			tdre_icsr=0;
	}
}
