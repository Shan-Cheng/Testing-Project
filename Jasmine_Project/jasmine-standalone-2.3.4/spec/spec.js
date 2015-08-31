describe("range test", function(){
   it("if no step, then return array with step 1", function(){
       expect(range(2,5)).toEqual([2,3,4,5]);
    });
    it("if no input return undefined", function(){
       expect(range()).toEqual(undefined);
    });
    it("if only one input return undefined", function(){
       expect(range(2)).toEqual(undefined);
    });
    it("return undefinded,if start larger than end and step is positive ", function(){
       expect(range(3,2,1)).toEqual(undefined);
    });
    it("return undefinded,if start smaller than end and step is negative ", function(){
       expect(range(2,4,-1)).toEqual(undefined);
    });
    it("return undefinded,if step == 0 ", function(){
       expect(range(2,4,0)).toEqual(undefined);
    });
    it("return one element,if start === end", function(){
       expect(range(2,2)).toEqual([2]);
    });
    it("return consective array,if start < end and step == undefined", function(){
       expect(range(2,6)).toEqual([2,3,4,5,6]);
    });
    it("return negative consective array,if start > end and step == undefined", function(){
       expect(range(6,2)).toEqual([6,5,4,3,2]);
    });
    it("return expected arrary", function(){
       expect(range(1,10,2)).toEqual([1,3,5,7,9]);
    });
      
    
});

describe("compute test", function(){
   it("find second largest and smallest", function(){
       expect(compute([1,2,3,4])).toEqual([2,3]);
    });
    it("undefined if no input", function(){
       expect(compute()).toEqual(undefined);
    });
    it("undefined if one input", function(){
       expect(compute([2])).toEqual(undefined);
    });
    it("return largest and smallest if only two input", function(){
       expect(compute([8,6])).toEqual([6,8]);
    });
     it("return one unmber if all are the same", function(){
       expect(compute([2,2])).toEqual([2]);
    });
});
describe("removeDuplicates test", function(){
      it("remove duplicate" , function(){   
       expect(removeDuplicates([2,2,3,1,5,6])).toEqual([1,2,3,5,6]);
    
    });
    it("return one number if all the same" , function(){   
       expect(removeDuplicates([2,2,2,2,2])).toEqual([2]);
    
    });
});

describe("hasDuplicates test", function(){
   it("return true if has duplicate number" , function(){   
       expect(hasDuplicates([2,2,3,1,5,6])).toBe(true);   
    });
    it("return false if has no duplicate number" , function(){   
       expect(hasDuplicates([2,4,6,7])).toBe(false);   
    });
});
describe("areConsecutive test", function(){
    it("return false if number are not consecutive" , function(){   
       expect(areConsecutive([2,4,6,7])).toBe(false);   
    });
    it("return false if not allow duplicate number" , function(){   
       expect(areConsecutive([1,2,3,3,4],false)).toBe(false);   
    });
    it("return true if allow duplicate number" , function(){   
       expect(areConsecutive([1,2,3,3,4],true)).toBe(true);   
    });
    it("return true if without duplicate note" , function(){   
       expect(areConsecutive([1,2,3,3,4])).toBe(true);   
    });
});