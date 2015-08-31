// Given start, end and step, returns an array with start and end both inclusive and seperated by step (positive or negative)
function range(start, end, step) {
     var result=[];
     
     if(start === undefined || end === undefined ){
        result = undefined;
         return result;
     } 
      
     if(start > end && step > 0){
        result = undefined;
         return result;    
     }
    
     if(start < end && step < 0) {
        result = undefined;
         return result;    
     } 
      
     if(step === 0 ){
        result = undefined;
         return result; 
     }
    
     if(start === end){
       result = [];
       result.push(start);
       return result;
     
     }
     
     if(step == undefined && start < end) {
        result =[];
        result.push(start);
        while (result[result.length-1]+1 <= end){
           result.push(result[result.length-1]+1);
        }
        return result;
     
     }
      if(step == undefined && start > end) {
        result =[];
        result.push(start);
        while (result[result.length-1]-1 >= end){
           result.push(result[result.length-1]-1);
        }
        return result;
     
     }
     
     if(step != undefined && end != undefined && step != undefined && start > end && step >0) {
         result = undefined;
         return result;
     }
     
     if(step != undefined && end != undefined && step != undefined && start < end && step <0) {
         result = undefined;
         return result;
     }
    
     if(step != undefined && end != undefined && step != undefined && start > end && step<0){
        result =[];
        result.push(start);
        while (result[result.length-1] + (step)>= end){
           result.push(result[result.length-1] +(step));
        }
        return result;
     
     
     }
     if(step != undefined && end != undefined && step != undefined && start < end && step>0){
        result =[];
        result.push(start);
        while (result[result.length-1] + (step)<= end){
           result.push(result[result.length-1] +(step));
        }
        return result;
     }
     
     
     
   
}
     
function compute(numbers) {
    var result = [];
    if(numbers === undefined || numbers.length == 1 ){
      result = undefined;
      return result;
    }
    var neworder =numbers.sort(function(a,b){return a-b});
    var diffarray = [];
    for( i=0; i<(neworder.length)-1 ; i++){
         diffarray.push(neworder[i+1]-neworder[i]);}
    var total =0;
    for (t=0 ; t < diffarray.length; t++){
       total = total+diffarray[t];
    }
    
    
       
    if (diffarray.length == 1 && total == 0){
           result.push(neworder[0]);
           return result;
       
       }else if (diffarray.length == 1 && total != 0){
           result = neworder;
           return result;
       }else if (diffarray.length >= 2 && total == 0){
           result.push(neworder[0]);
           return result;
       }else if (diffarray.length >= 2 && total != 0){
          for ( j=0 ; j<diffarray.length; j++){
           if(diffarray[j] == 0){
           neworder.splice(j,1);
          }
       }    
    }
   // console.log(neworder);
    if (neworder.length <=2) {
       result = neworder;
        return result;
    }
    if(neworder.length ==3){
      result.push(neworder[1]);
        return result;
    }
    if(neworder.length >3){
       result.push(neworder[1]);
       result.push(neworder[neworder.length-2]);
       return result;
    
    }
    
   };
function removeDuplicates(numbers){
  numbers.sort(function(a,b){return a-b});
  var result = [];
   for (i=0 ; i< numbers.length ; i++) {
        if (numbers[i] == numbers[i+1]){
            numbers.splice(i,1);}
   }
    for (j=0 ; j < numbers.length; j++){
        if(numbers[j] == numbers[j+1]){
                result.push(numbers[j]);
                return result;
        }else  {
           result.push(numbers[j]);}
    
    }
    return result;
};
function hasDuplicates(numbers){
   var result = false;
   numbers.sort(function(a,b){return a-b});
   for (i=0 ; i< numbers.length ; i++) {
        if (numbers[i] == numbers[i+1]){
            result = true;}
   }
   return result;

};

function areConsecutive(numbers, allowDuplicates){
   
    if (allowDuplicates == true || allowDuplicates == undefined ){
     numbers.sort(function(a,b){return a-b});
     for (i=0 ; i<numbers.length;i++){
         if (numbers[i]==numbers[i+1]){
             numbers.splice(i,1);
         }
     };
      
     
     if(numbers.length === (numbers[numbers.length-1]-numbers[0]+1) ){
        return true;
     }else return false;
      
 
   }else if (allowDuplicates == false) {
         if(hasDuplicates(numbers) == true) {
         return false;}
   
         else {
            numbers.sort(function(a,b){return a-b});
     for (i=0 ; i<numbers.length;i++){
         if (numbers[i]==numbers[i+1]){
             numbers.splice(i,1);
         }
     };
      
     
     if(numbers.length === (numbers[numbers.length-1]-numbers[0]+1) ){
        return true;
     }else return false;

         }
      
   }
   
   }