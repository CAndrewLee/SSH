// 验证表单输入不为空的脚本代码   
function checkForm(form) {   
    if (form.bookname.value == "") {   
        alert("书名不能为空!");   
        form.bookname.focus();   
        return false;   
    }   
    if (form.bookauthor.value == "") {   
        alert("作者不能为空!");   
        form.bookauthor.focus();   
        return false;   
    }   
    if (form.bookprice.value == "") {   
        alert("价格不能为空!");   
        form.bookprice.focus();   
        return false;   
    }   
    return true;   
}   
function checkSearchForm(form){   
    if(form.bookname.value.match(/^\s*$/)){   
        alert("查询条件不能为空!");   
        form.bookname.focus();   
        return false;   
    }   
    return true;   
}  


