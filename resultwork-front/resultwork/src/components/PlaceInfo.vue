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
        style="width: 20%;">
      </el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        style="width: 20%;">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        style="width: 20%;">
      </el-table-column>
      <el-table-column
        prop="maxAccommodate"
        label="最大容纳人数"
        style="width: 20%;">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        style="width: 20%;">
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
        <el-form-item label="名称">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="formData.address"></el-input>
        </el-form-item>
        <el-form-item label="最大容纳人数">
          <el-input v-model="formData.maxAccommodate" type="number"></el-input>
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
      tableData: [],
      dialogVisible: false,
      formData: {
        id: null,
        name: '',
        address: '',
        maxAccommodate: null
      },
      isEdit: false
    };
  },
  computed: {
    filteredData() {
      if (this.searchQuery) {
        return this.tableData.filter(place =>
          place.name.includes(this.searchQuery) ||
          place.address.includes(this.searchQuery)
        );
      }
      return this.tableData;
    }
  },
  methods: {
    fetchPlaces() {
      axios.get('/place/list')
        .then(response => {
          this.tableData = response.data.places;
        })
        .catch(error => {
          this.handleError('获取数据失败', error);
        });
    },
    handleSearch() {
      // This method is called on input event of the search bar
    },
    handleAdd() {
      this.isEdit = false;
      this.formData = { id: null, name: '', address: '', maxAccommodate: null };
      this.dialogVisible = true;
    },
    handleEdit(row) {
      this.isEdit = true;
      this.formData = { ...row };
      this.dialogVisible = true;
    },

    handleDelete(row) {
      axios.post('/place/del', { id: row.id })
        .then(response => {
          if (response.data.isOk) {
            const index = this.tableData.indexOf(row);
            if (index !== -1) {
              this.tableData.splice(index, 1);
            }
          } 
        }).catch(error => {
          console.error('Failed to delete data:', error);
        });
    },

  
    saveData() {
      if (this.isEdit) {
        axios.post('/place/update', this.formData)
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
        axios.post('/place/add', this.formData)
          .then(response => {
            if (response.data.isOk) {
              this.tableData.push({ ...this.formData });
              this.dialogVisible = false;
              this.resetFormData();
              this.fetchPlaces();
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
        id: null,
        name: '',
        address: '',
        maxAccommodate: null
      };
    },
  
  },
  mounted() {
    this.fetchPlaces();
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
