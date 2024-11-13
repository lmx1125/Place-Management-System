<template>
    <div>
      <!-- Search Bar -->
      <el-input
        v-model="searchQuery"
        placeholder="搜索"
        class="search-bar"
        @input="handleSearch">
      </el-input>
  
      <!-- Data Table -->
      <el-table
        :data="filteredData"
        border
        style="width: 100%">
        <el-table-column
          fixed
          prop="id"
          label="ID"
          width="150">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="150">
        </el-table-column>
        <el-table-column
          prop="gender"
          label="性别"
          width="120">
        </el-table-column>
        <el-table-column
          prop="beginTime"
          label="进场时间"
          width="400">
        </el-table-column>
        <el-table-column
          prop="place.name"
          label="地址"
          width="500">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="150">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- Add Button -->
      <div class="add-button">
        <el-button type="primary" @click="handleAdd">添加</el-button>
      </div>
  
      <!-- Add/Edit Data Form -->
      <el-dialog :visible.sync="dialogVisible" title="添加/编辑数据">
        <el-form :model="formData">
          <el-form-item label="ID">
            <el-input v-model="formData.id" :disabled="isEdit"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="formData.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="formData.gender"></el-input>
          </el-form-item>
          <el-form-item label="进场时间">
            <el-input v-model="formData.beginTime"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="formData.place.name"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveData">确认</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        searchQuery: '',
        dialogVisible: false,
        isEdit: false,
        formData: {
          id: '',
          name: '',
          gender: '',
          beginTime: '',
          place: {
            name: ''
          }
        },
        tableData: []
      };
    },
    computed: {
      filteredData() {
        if (this.searchQuery) {
          return this.tableData.filter(row => 
            Object.values(row).some(val => 
              typeof val === 'string' && val.includes(this.searchQuery)
            ) || Object.values(row.place).some(val =>
              typeof val === 'string' && val.includes(this.searchQuery)
            )
          );
        }
        return this.tableData;
      }
    },
    methods: {
      fetchData() {
        axios.get('/student/list')
          .then(response => {
            this.tableData = response.data.students;
          })
          .catch(error => {
            console.error('Failed to fetch data:', error);
          });
      },
      handleSearch() {
        // Logic for handling search can be placed here if necessary
      },
      handleAdd() {
        this.isEdit = false;
        this.dialogVisible = true;
        this.resetFormData();
      },
      handleEdit(row) {
        this.isEdit = true;
        this.dialogVisible = true;
        this.formData = JSON.parse(JSON.stringify(row)); // Clone the row data to formData
      },
      handleDelete(row) {
        axios.post('/student/del', { id: row.id })
          .then(response => {
            if (response.data.isOk) {
              const index = this.tableData.indexOf(row);
              if (index !== -1) {
                this.tableData.splice(index, 1);
              }
            } else {
              console.error('Delete failed:', response.data.msg);
            }
          })
          .catch(error => {
            console.error('Failed to delete data:', error);
          });
      },
      saveData() {
        if (this.isEdit) {
          axios.post('/student/update', this.formData)
            .then(response => {
              if (response.data.isOk) {
                const index = this.tableData.findIndex(item => item.id === this.formData.id);
                if (index !== -1) {
                  this.tableData.splice(index, 1, { ...this.formData });
                }
                this.dialogVisible = false;
                this.resetFormData();
              } else {
                console.error('Update failed:', response.data.msg);
              }
            })
            .catch(error => {
              console.error('Failed to update data:', error);
            });
        } else {
          axios.post('/student/add', this.formData)
            .then(response => {
              if (response.data.isOk) {
                this.tableData.push({ ...this.formData });
                this.dialogVisible = false;
                this.resetFormData();
                this.fetchData();
              } else {
                console.error('Add failed:', response.data.msg);
              }
            })
            .catch(error => {
              console.error('Failed to add data:', error);
            });
        }
      },
      resetFormData() {
        this.formData = {
          id: '',
          name: '',
          gender: '',
          beginTime: '',
          place: {
            name: ''
          }
        };
      }
    },
    mounted() {
      this.fetchData();
    }
  };
  </script>
  
  
    <style>
  .search-bar {
    margin-bottom: 20px;
  }
  .add-button {
    margin-top: 20px;
    text-align: right;
  }
  </style>