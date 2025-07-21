# 🚗 Car Management API

---

## 📝 Overview
A complete Car Fleet Management System consisting of a Backend REST API and a Frontend application consuming the API without modifications.

## ✅ Features
- **Service Centers Management:** Create, update, delete, and list service centers with filtering and capacity reports.
- **Cars Management:** Manage cars (CRUD) with filters by brand, service center, and production year. Cars can be registered in multiple service centers.
- **Maintenance Requests Management:** Create, update, delete, and list maintenance requests with availability checks. Monthly reports including months with zero requests.

## 🔗 API Specifications
- Follows given OpenAPI definition.
- Proper HTTP status codes: 400 (bad request), 404 (not found), 200 (success).
- Date formats: `yyyy-mm-dd` for dates, `yyyy-mm` for months.

