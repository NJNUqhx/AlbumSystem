<template>

<form>
  <div>
    <label for="profile_pic">Choose file to upload</label>
    <input type="file" id="profile_pic" name="profile_pic"
          accept=".jpg">
  </div>
  <div>
    <button>Submit</button>
  </div>
</form>

</template>

<script>
export default{
  name: "UploadPhoto"
}

</script>